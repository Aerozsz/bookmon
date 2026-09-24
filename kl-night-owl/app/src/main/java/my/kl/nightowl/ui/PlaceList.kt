package my.kl.nightowl.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import my.kl.nightowl.core.HoursText
import my.kl.nightowl.core.Place
import my.kl.nightowl.data.dial
import my.kl.nightowl.data.openWebsite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceList(
    places: List<PlaceUi>,
    hoursText: HoursText,
    todayIndex: Int,
    refreshing: Boolean,
    footer: String,
    onRefresh: () -> Unit,
    onShowOnMap: (Place) -> Unit,
    onDirections: (Place) -> Unit,
    lookupAddress: suspend (Place) -> String?,
) {
    var expandedId by rememberSaveable { mutableStateOf<String?>(null) }
    PullToRefreshBox(isRefreshing = refreshing, onRefresh = onRefresh, modifier = Modifier.fillMaxSize()) {
        if (places.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 32.dp, vertical = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("🦉", style = MaterialTheme.typography.displayMedium)
                Spacer(Modifier.height(12.dp))
                Text(
                    "Nothing matches these filters.\nTry another category or clear the search.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("place_list"),
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(places, key = { it.place.id }) { ui ->
                    PlaceCard(
                        ui = ui,
                        hoursText = hoursText,
                        todayIndex = todayIndex,
                        expanded = expandedId == ui.place.id,
                        onToggle = { expandedId = if (expandedId == ui.place.id) null else ui.place.id },
                        onShowOnMap = { onShowOnMap(ui.place) },
                        onDirections = { onDirections(ui.place) },
                        lookupAddress = lookupAddress,
                    )
                }
                item(key = "footer") {
                    Text(
                        footer,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun PlaceCard(
    ui: PlaceUi,
    hoursText: HoursText,
    todayIndex: Int,
    expanded: Boolean,
    onToggle: () -> Unit,
    onShowOnMap: () -> Unit,
    onDirections: () -> Unit,
    lookupAddress: suspend (Place) -> String?,
) {
    val place = ui.place
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("place_card"),
    ) {
        Column(
            Modifier
                .clickable(onClick = onToggle)
                .padding(14.dp),
        ) {
            Row(verticalAlignment = Alignment.Top) {
                CategoryBadge(place.category)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        place.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        place.kind,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (!expanded && place.address != null) {
                        Text(
                            place.address,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                Spacer(Modifier.width(8.dp))
                StatusPill(ui)
            }

            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.NightsStay, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text(
                    "Tonight: ${ui.tonightText}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f),
                )
                formatDistance(ui.distanceMeters)?.let {
                    Text(it, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.secondary)
                }
            }
            if (!place.schedule.isAlwaysOpen && ui.statusText != ui.tonightText) {
                Text(
                    ui.statusText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 24.dp, top = 2.dp),
                )
            }
            if (!place.hoursVerified) {
                Text(
                    "Usually open 24h for this chain · hours not confirmed on the map",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(start = 24.dp, top = 2.dp),
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column(Modifier.padding(top = 12.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(Modifier.height(10.dp))
                    Text("Opening hours", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.height(4.dp))
                    WeekTable(place, hoursText, todayIndex)
                    place.openingHoursRaw?.let {
                        Text(
                            "As written on OpenStreetMap: $it",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 6.dp),
                        )
                    }
                    rememberAddress(place, lookupAddress)?.let { InfoRow(Icons.Filled.Place, it) }
                    place.phone?.let { phone -> InfoRow(Icons.Filled.Phone, phone) { dial(context, phone) } }
                    place.website?.let { url -> InfoRow(Icons.Filled.Language, url) { openWebsite(context, url) } }
                }
            }

            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "Hide details" else "Show details",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    if (expanded) "Less" else "Hours & details",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.weight(1f))
                OutlinedButton(onClick = onShowOnMap, contentPadding = PaddingValues(horizontal = 12.dp), modifier = Modifier.testTag("map_button")) {
                    Icon(Icons.Filled.Map, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Map")
                }
                Spacer(Modifier.width(8.dp))
                Button(onClick = onDirections, contentPadding = PaddingValues(horizontal = 14.dp), modifier = Modifier.testTag("go_button")) {
                    Icon(Icons.Filled.Directions, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Go")
                }
            }
        }
    }
}

@Composable
private fun WeekTable(place: Place, hoursText: HoursText, todayIndex: Int) {
    for (d in 0..6) {
        val today = d == todayIndex
        val color = if (today) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        Row(Modifier.padding(vertical = 2.dp)) {
            Text(
                HoursText.DAY_NAMES[d] + if (today) " (today)" else "",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (today) FontWeight.Bold else FontWeight.Normal,
                color = color,
                modifier = Modifier.width(110.dp),
            )
            Text(
                hoursText.day(place.schedule.days[d]),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (today) FontWeight.Bold else FontWeight.Normal,
                color = color,
            )
        }
    }
}

@Composable
private fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String, onClick: (() -> Unit)? = null) {
    val base = Modifier.fillMaxWidth().padding(top = 8.dp)
    Row(
        modifier = if (onClick != null) base.clickable(onClick = onClick) else base,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(8.dp))
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            color = if (onClick != null) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface,
        )
    }
}
