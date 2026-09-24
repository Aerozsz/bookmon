package my.kl.nightowl.ui.map

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import my.kl.nightowl.core.Category
import my.kl.nightowl.ui.FocusRequest
import my.kl.nightowl.ui.PlaceUi

/** In-app map drawn by the Google Maps SDK. Only used when the app was built with a Maps API key. */
@Composable
fun GoogleNightMap(
    places: List<PlaceUi>,
    selectedId: String?,
    darkMap: Boolean,
    showMyLocation: Boolean,
    focus: FocusRequest?,
    startLat: Double,
    startLon: Double,
    startZoom: Double,
    onSelect: (String?) -> Unit,
    onCameraSaved: (lat: Double, lon: Double, zoom: Double) -> Unit,
    onFocusConsumed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current.density
    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(startLat, startLon), startZoom.toFloat())
    }

    LaunchedEffect(focus) {
        focus?.let {
            cameraState.animate(CameraUpdateFactory.newLatLngZoom(LatLng(it.lat, it.lon), it.zoom.toFloat()), 600)
            onFocusConsumed()
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            val p = cameraState.position
            onCameraSaved(p.target.latitude, p.target.longitude, p.zoom.toDouble())
        }
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraState,
        properties = MapProperties(isMyLocationEnabled = showMyLocation, minZoomPreference = 9f),
        uiSettings = MapUiSettings(
            mapToolbarEnabled = false,
            myLocationButtonEnabled = false,
            zoomControlsEnabled = false,
            tiltGesturesEnabled = false,
        ),
        mapColorScheme = if (darkMap) ComposeMapColorScheme.DARK else ComposeMapColorScheme.LIGHT,
        onMapClick = { onSelect(null) },
    ) {
        val icons = remember { HashMap<Pair<Category, Boolean>, BitmapDescriptor>() }
        for (ui in places) {
            key(ui.place.id) {
                val state = remember { MarkerState(position = LatLng(ui.place.lat, ui.place.lon)) }
                val selected = ui.place.id == selectedId
                Marker(
                    state = state,
                    title = ui.place.name,
                    snippet = ui.place.kind,
                    icon = icons.getOrPut(ui.place.category to selected) { markerIcon(ui.place.category, selected, density) },
                    anchor = Offset(0.5f, 0.5f),
                    alpha = if (ui.openNow || selected) 1f else 0.55f,
                    zIndex = if (selected) 10f else 0f,
                    onClick = {
                        onSelect(ui.place.id)
                        true
                    },
                )
            }
        }
    }
}

private fun markerIcon(category: Category, selected: Boolean, density: Float): BitmapDescriptor {
    val radius = (if (selected) 17f else 12f) * density
    val stroke = (if (selected) 3f else 2f) * density
    val size = ((radius + stroke) * 2).toInt() + 2
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val c = size / 2f
    val fill = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = category.colorArgb.toInt() }
    val ring = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = stroke
        color = if (selected) 0xFFFFFFFF.toInt() else 0xFF0A0E1A.toInt()
    }
    canvas.drawCircle(c, c, radius, fill)
    canvas.drawCircle(c, c, radius, ring)
    val text = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = (if (selected) 16f else 11f) * density
    }
    val fm = text.fontMetrics
    canvas.drawText(category.emoji, c, c - (fm.ascent + fm.descent) / 2f, text)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}
