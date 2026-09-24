package my.kl.nightowl.ui.map

import android.location.Location
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import my.kl.nightowl.ui.FocusRequest
import my.kl.nightowl.ui.PlaceUi
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.CopyrightOverlay

private const val CARTO_CREDIT = "© OpenStreetMap contributors © CARTO"

private val DARK_TILES = XYTileSource(
    "CartoDarkMatter", 0, 20, 256, "@2x.png",
    arrayOf(
        "https://a.basemaps.cartocdn.com/dark_all/",
        "https://b.basemaps.cartocdn.com/dark_all/",
        "https://c.basemaps.cartocdn.com/dark_all/",
        "https://d.basemaps.cartocdn.com/dark_all/",
    ),
    CARTO_CREDIT,
)

private val LIGHT_TILES = XYTileSource(
    "CartoVoyager", 0, 20, 256, "@2x.png",
    arrayOf(
        "https://a.basemaps.cartocdn.com/rastertiles/voyager/",
        "https://b.basemaps.cartocdn.com/rastertiles/voyager/",
        "https://c.basemaps.cartocdn.com/rastertiles/voyager/",
        "https://d.basemaps.cartocdn.com/rastertiles/voyager/",
    ),
    CARTO_CREDIT,
)

/** In-app map built on OpenStreetMap. Needs no API key. */
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
        modifier = modifier,
        update = { view ->
            val tiles = if (darkMap) DARK_TILES else LIGHT_TILES
            if (view.tileProvider.tileSource.name() != tiles.name()) view.setTileSource(tiles)
            overlay.places = places
            overlay.selectedId = selectedId
            overlay.user = userLocation?.let { GeoPoint(it.latitude, it.longitude) }
            view.invalidate()
        },
    )
}
