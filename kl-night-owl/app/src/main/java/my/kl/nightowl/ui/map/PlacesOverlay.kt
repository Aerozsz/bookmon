package my.kl.nightowl.ui.map

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.RectF
import android.graphics.Typeface
import android.view.MotionEvent
import androidx.annotation.VisibleForTesting
import my.kl.nightowl.ui.PlaceUi
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.Projection
import org.osmdroid.views.overlay.Overlay
import kotlin.math.PI
import kotlin.math.floor
import kotlin.math.hypot
import kotlin.math.ln
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.tan

/**
 * Draws every place as a coloured dot with its category emoji. When zoomed out, nearby places
 * are grouped into numbered bubbles; tapping a bubble zooms in, tapping a dot selects the place.
 */
class PlacesOverlay(
    private val density: Float,
    private val onPlaceTap: (String?) -> Unit,
) : Overlay() {

    var places: List<PlaceUi> = emptyList()
    var selectedId: String? = null
    var user: GeoPoint? = null

    private class Drawn(val x: Float, val y: Float, val radius: Float, val placeId: String?, val lat: Double, val lon: Double)

    private var drawn: List<Drawn> = emptyList()
    private val reusePoint = Point()
    private val reuseGeo = GeoPoint(0.0, 0.0)

    private val fill = Paint(Paint.ANTI_ALIAS_FLAG)
    private val ring = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2f * density
        color = 0xFF0A0E1A.toInt()
    }
    private val selectedRing = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3f * density
        color = 0xFFFFFFFF.toInt()
    }
    private val emoji = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 11f * density
    }
    private val count = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 12f * density
        typeface = Typeface.DEFAULT_BOLD
        color = 0xFF2B1D00.toInt()
    }
    private val labelText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 13f * density
        typeface = Typeface.DEFAULT_BOLD
        color = 0xFFFFFFFF.toInt()
    }
    private val labelBg = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xE6141A2E.toInt() }
    private val userFill = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF4C8DFF.toInt() }
    private val userHalo = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0x404C8DFF }
    private val userRing = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2.5f * density
        color = 0xFFFFFFFF.toInt()
    }

    override fun draw(canvas: Canvas, projection: Projection) {
        val box = projection.boundingBox
        val latPad = (box.latNorth - box.latSouth) * 0.1
        val lonPad = (box.lonEast - box.lonWest) * 0.1
        val inView = places.filter {
            it.place.lat <= box.latNorth + latPad && it.place.lat >= box.latSouth - latPad &&
                it.place.lon <= box.lonEast + lonPad && it.place.lon >= box.lonWest - lonPad
        }
        val zoom = projection.zoomLevel
        val out = ArrayList<Drawn>(inView.size)
        val selected = inView.firstOrNull { it.place.id == selectedId }
        val others = inView.filter { it !== selected }

        if (zoom < CLUSTER_UNTIL_ZOOM) {
            // Group places falling in the same grid cell (fixed to the map, so bubbles don't jump when panning).
            val world = 256.0 * 2.0.pow(floor(zoom)) * density
            val cell = 58.0 * density
            val groups = LinkedHashMap<Long, MutableList<PlaceUi>>()
            for (p in others) {
                val gx = floor(mercX(p.place.lon) * world / cell).toLong()
                val gy = floor(mercY(p.place.lat) * world / cell).toLong()
                groups.getOrPut((gx shl 32) xor (gy and 0xffffffffL)) { mutableListOf() }.add(p)
            }
            for (group in groups.values) {
                if (group.size == 1) {
                    out += drawPlace(canvas, projection, group[0], selected = false)
                } else {
                    out += drawCluster(canvas, projection, group)
                }
            }
        } else {
            for (p in others) out += drawPlace(canvas, projection, p, selected = false)
        }
        user?.let { drawUser(canvas, projection, it) }
        if (selected != null) out += drawPlace(canvas, projection, selected, selected = true)
        drawn = out
    }

    private fun toScreen(projection: Projection, lat: Double, lon: Double): Point {
        reuseGeo.setCoords(lat, lon)
        return projection.toPixels(reuseGeo, reusePoint)
    }

    private fun drawPlace(canvas: Canvas, projection: Projection, ui: PlaceUi, selected: Boolean): Drawn {
        val p = toScreen(projection, ui.place.lat, ui.place.lon)
        val x = p.x.toFloat()
        val y = p.y.toFloat()
        val radius = (if (selected) 17f else 12f) * density
        fill.color = ui.place.category.colorArgb.toInt()
        fill.alpha = if (ui.openNow || selected) 255 else 130
        canvas.drawCircle(x, y, radius, fill)
        canvas.drawCircle(x, y, radius, if (selected) selectedRing else ring)
        emoji.textSize = (if (selected) 16f else 11f) * density
        emoji.alpha = fill.alpha
        val fm = emoji.fontMetrics
        canvas.drawText(ui.place.category.emoji, x, y - (fm.ascent + fm.descent) / 2f, emoji)
        if (selected) drawLabel(canvas, ui.place.name, x, y - radius - 10f * density)
        return Drawn(x, y, radius, ui.place.id, ui.place.lat, ui.place.lon)
    }

    private fun drawCluster(canvas: Canvas, projection: Projection, group: List<PlaceUi>): Drawn {
        val lat = group.sumOf { it.place.lat } / group.size
        val lon = group.sumOf { it.place.lon } / group.size
        val p = toScreen(projection, lat, lon)
        val x = p.x.toFloat()
        val y = p.y.toFloat()
        val radius = (15f + min(11f, sqrt(group.size.toFloat()) * 1.6f)) * density
        fill.color = 0xFFFFC857.toInt()
        fill.alpha = 235
        canvas.drawCircle(x, y, radius + 4f * density, fill.apply { alpha = 70 })
        fill.alpha = 235
        canvas.drawCircle(x, y, radius, fill)
        canvas.drawCircle(x, y, radius, ring)
        val fm = count.fontMetrics
        canvas.drawText(group.size.toString(), x, y - (fm.ascent + fm.descent) / 2f, count)
        return Drawn(x, y, radius, null, lat, lon)
    }

    private fun drawLabel(canvas: Canvas, text: String, cx: Float, bottom: Float) {
        val label = if (text.length > 34) text.take(32) + "…" else text
        val w = labelText.measureText(label)
        val fm = labelText.fontMetrics
        val h = fm.descent - fm.ascent
        val padX = 10f * density
        val padY = 6f * density
        val rect = RectF(cx - w / 2 - padX, bottom - h - 2 * padY, cx + w / 2 + padX, bottom)
        canvas.drawRoundRect(rect, 10f * density, 10f * density, labelBg)
        canvas.drawText(label, cx, rect.bottom - padY - fm.descent, labelText)
    }

    private fun drawUser(canvas: Canvas, projection: Projection, at: GeoPoint) {
        val p = toScreen(projection, at.latitude, at.longitude)
        val x = p.x.toFloat()
        val y = p.y.toFloat()
        canvas.drawCircle(x, y, 18f * density, userHalo)
        canvas.drawCircle(x, y, 7f * density, userFill)
        canvas.drawCircle(x, y, 7f * density, userRing)
    }

    /** View coordinates of the numbered group bubbles drawn in the last frame. */
    @VisibleForTesting
    fun groupPositions(): List<Pair<Float, Float>> = drawn.filter { it.placeId == null }.map { it.x to it.y }

    override fun onSingleTapConfirmed(e: MotionEvent, mapView: MapView): Boolean {
        val hit = drawn
            .map { it to hypot(it.x - e.x, it.y - e.y) }
            .filter { (d, dist) -> dist <= d.radius + 10f * density }
            .minByOrNull { it.second }
            ?.first
        if (hit == null) {
            if (selectedId != null) {
                onPlaceTap(null)
                return true
            }
            return false
        }
        if (hit.placeId != null) {
            onPlaceTap(hit.placeId)
        } else {
            mapView.controller.animateTo(GeoPoint(hit.lat, hit.lon), min(mapView.zoomLevelDouble + 2.0, 18.0), 450L)
        }
        return true
    }

    private fun mercX(lon: Double) = (lon + 180.0) / 360.0
    private fun mercY(lat: Double): Double {
        val r = Math.toRadians(lat)
        return (1.0 - ln(tan(r) + 1.0 / kotlin.math.cos(r)) / PI) / 2.0
    }

    companion object {
        const val CLUSTER_UNTIL_ZOOM = 15.0
    }
}
