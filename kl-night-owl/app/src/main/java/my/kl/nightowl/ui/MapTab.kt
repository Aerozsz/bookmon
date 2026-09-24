package my.kl.nightowl.ui

import android.location.Location
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import my.kl.nightowl.BuildConfig
import my.kl.nightowl.core.Place
import my.kl.nightowl.data.TravelMode
import my.kl.nightowl.ui.map.GoogleNightMap
import my.kl.nightowl.ui.map.OsmNightMap

/** True when the app was built with a Google Maps key; otherwise the map uses OpenStreetMap. */
val USES_GOOGLE_MAP_TILES: Boolean = BuildConfig.MAPS_API_KEY.isNotBlank()

@Composable
fun MapTab(
    vm: NightOwlViewModel,
    places: List<PlaceUi>,
    selectedId: String?,
    focus: FocusRequest?,
    userLocation: Location?,
    hasLocationPermission: Boolean,
    travelMode: TravelMode,
    onRequestLocation: () -> Unit,
    onDirections: (Place) -> Unit,
) {
    val context = LocalContext.current
    var darkMap by remember { mutableStateOf(vm.prefs.darkMap) }
    val selected = places.firstOrNull { it.place.id == selectedId }
    // Keeps the card's content while it slides away after the selection is cleared.
    val lastSelected = remember { arrayOfNulls<PlaceUi>(1) }
    if (selected != null) lastSelected[0] = selected

    Box(Modifier.fillMaxSize()) {
        if (USES_GOOGLE_MAP_TILES) {
            GoogleNightMap(
                places = places,
                selectedId = selectedId,
                darkMap = darkMap,
                showMyLocation = hasLocationPermission,
                focus = focus,
                startLat = vm.cameraLat,
                startLon = vm.cameraLon,
                startZoom = vm.cameraZoom,
                onSelect = vm::select,
                onCameraSaved = { lat, lon, zoom -> vm.cameraLat = lat; vm.cameraLon = lon; vm.cameraZoom = zoom },
                onFocusConsumed = vm::focusConsumed,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            OsmNightMap(
                places = places,
                selectedId = selectedId,
                darkMap = darkMap,
                userLocation = userLocation,
                focus = focus,
                startLat = vm.cameraLat,
                startLon = vm.cameraLon,
                startZoom = vm.cameraZoom,
                onSelect = vm::select,
                onCameraSaved = { lat, lon, zoom -> vm.cameraLat = lat; vm.cameraLon = lon; vm.cameraZoom = zoom },
                onFocusConsumed = vm::focusConsumed,
                modifier = Modifier.fillMaxSize(),
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            SmallFloatingActionButton(
                onClick = {
                    darkMap = !darkMap
                    vm.prefs.darkMap = darkMap
                },
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            ) { Icon(Icons.Filled.Layers, contentDescription = "Switch map style") }
            SmallFloatingActionButton(
                onClick = {
                    when {
                        !hasLocationPermission -> onRequestLocation()
                        userLocation == null -> Toast.makeText(context, "Finding your location…", Toast.LENGTH_SHORT).show()
                        else -> vm.focusOn(userLocation.latitude, userLocation.longitude, 16.0)
                    }
                },
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            ) { Icon(Icons.Filled.MyLocation, contentDescription = "Show my location") }
        }

        AnimatedVisibility(
            visible = selected != null,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
        ) {
            (selected ?: lastSelected[0])?.let { ui ->
                SelectedPlaceCard(
                    ui = ui,
                    travelMode = travelMode,
                    onTravelMode = vm::setTravelMode,
                    onDirections = { onDirections(ui.place) },
                    onClose = { vm.select(null) },
                )
            }
        }
    }
}

@Composable
private fun SelectedPlaceCard(
    ui: PlaceUi,
    travelMode: TravelMode,
    onTravelMode: (TravelMode) -> Unit,
    onDirections: () -> Unit,
    onClose: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                CategoryBadge(ui.place.category)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        ui.place.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        ui.place.kind,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                IconButton(onClick = onClose, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Filled.Close, contentDescription = "Close")
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                StatusPill(ui)
                Spacer(Modifier.width(8.dp))
                Icon(Icons.Filled.NightsStay, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text(
                    ui.tonightText,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                formatDistance(ui.distanceMeters)?.let {
                    Text(it, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.secondary)
                }
            }
            if (!ui.place.schedule.isAlwaysOpen) {
                Text(
                    ui.statusText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            ui.place.address?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TravelMode.entries.forEach { mode ->
                    FilterChip(
                        selected = mode == travelMode,
                        onClick = { onTravelMode(mode) },
                        label = { Text(mode.label) },
                    )
                }
            }
            Spacer(Modifier.height(6.dp))
            Button(onClick = onDirections, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Icon(Icons.Filled.Directions, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Start route in Google Maps", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
