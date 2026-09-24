package my.kl.nightowl.ui.map

import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.location.Location
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import my.kl.nightowl.ui.FocusRequest
import my.kl.nightowl.ui.PlaceUi
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.CopyrightOverlay

/**
 * Night look for the standard OpenStreetMap tiles: invert, turn the hues back (water stays blue,
 * parks green), then dim towards the app's navy. Done on the phone, so no tile account is needed.
 */
private val NIGHT_FILTER: ColorFilter = ColorMatrix(
    floatArrayOf(
        -1f, 0f, 0f, 0f, 255f,
        0f, -1f, 0f, 0f, 255f,
        0f, 0f, -1f, 0f, 255f,
        0f, 0f, 0f, 1f, 0f,
    ),
).apply {
    postConcat(
        ColorMatrix(
            floatArrayOf(
                -0.574f, 1.430f, 0.144f, 0f, 0f,
                0.426f, 0.430f, 0.144f, 0f, 0f,
                0.426f, 1.430f, -0.856f, 0f, 0f,
                0f, 0f, 0f, 1f, 0f,
            ),
        ),
    )
    postConcat(
        ColorMatrix(
            floatArrayOf(
                0.85f, 0f, 0f, 0f, 6f,
                0f, 0.85f, 0f, 0f, 10f,
                0f, 0f, 0.9f, 0f, 26f,
                0f, 0f, 0f, 1f, 0f,
            ),
        ),
    )
}.let { ColorMatrixColorFilter(it) }

/** In-app map built on OpenStreetMap's own tiles. Needs no API key. */
@Composable
fun OsmNightMap(
    places: List<PlaceUi>,
    selectedId: String?,
    darkMap: Boolean,
    userLocation: Location?,
    focus: FocusRequest?,
    startLat: Double,
    startLon: Double,
    startZoom: Double,
    onSelect: (String?) -> Unit,
    onCameraSaved: (lat: Double, lon: Double, zoom: Double) -> Unit,
    onFocusConsumed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val density = LocalDensity.current.density
    val currentOnSelect by rememberUpdatedState(onSelect)
    val currentOnCameraSaved by rememberUpdatedState(onCameraSaved)

    val overlay = remember { PlacesOverlay(density) { id -> currentOnSelect(id) } }
    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
            setTilesScaledToDpi(true)
            setHorizontalMapRepetitionEnabled(false)
            setVerticalMapRepetitionEnabled(false)
            setMinZoomLevel(10.0)
            setMaxZoomLevel(19.5)
            setScrollableAreaLimitDouble(BoundingBox(3.60, 102.05, 2.70, 101.30))
            controller.setZoom(startZoom)
            controller.setCenter(GeoPoint(startLat, startLon))
            overlays.add(overlay)
            overlays.add(CopyrightOverlay(context).apply { setTextColor(0xFF9AA3BA.toInt()) })
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            val center = mapView.mapCenter
            currentOnCameraSaved(center.latitude, center.longitude, mapView.zoomLevelDouble)
            mapView.onDetach()
        }
    }

    LaunchedEffect(focus) {
        focus?.let {
            mapView.controller.animateTo(GeoPoint(it.lat, it.lon), it.zoom, 600L)
            onFocusConsumed()
        }
    }

    AndroidView(
        factory = { mapView },
        // osmdroid paints tiles beyond its own edges; keep it inside its box.
        modifier = modifier.clipToBounds(),
        update = { view ->
            view.mapOverlay.setColorFilter(if (darkMap) NIGHT_FILTER else null)
            view.contentDescription = if (darkMap) "Night map" else "Street map"
            overlay.places = places
            overlay.selectedId = selectedId
            overlay.user = userLocation?.let { GeoPoint(it.latitude, it.longitude) }
            view.invalidate()
        },
    )
}
