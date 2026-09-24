package my.kl.nightowl.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.kl.nightowl.BuildConfig
import my.kl.nightowl.core.Category
import my.kl.nightowl.data.PlacesRepository
import my.kl.nightowl.data.openDirections
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val TAB_LIST = 0
private const val TAB_MAP = 1

private val LOCATION_PERMISSIONS = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION,
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun NightOwlScreen(vm: NightOwlViewModel) {
    val data by vm.data.collectAsStateWithLifecycle()
    val filters by vm.filters.collectAsStateWithLifecycle()
    val visible by vm.visible.collectAsStateWithLifecycle()
    val counts by vm.categoryCounts.collectAsStateWithLifecycle()
    val now by vm.now.collectAsStateWithLifecycle()
    val userLocation by vm.locationTracker.location.collectAsStateWithLifecycle()
    val selectedId by vm.selectedId.collectAsStateWithLifecycle()
    val focus by vm.focus.collectAsStateWithLifecycle()
    val travelMode by vm.travelMode.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var tab by rememberSaveable { mutableIntStateOf(TAB_LIST) }
    var showAbout by rememberSaveable { mutableStateOf(false) }
    val snackbar = remember { SnackbarHostState() }

    // ---- location permission: asked once on first launch, and again from the map's location button
    var hasLocation by remember { mutableStateOf(vm.locationTracker.hasPermission()) }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
        hasLocation = result.values.any { it }
        if (hasLocation) vm.locationTracker.start()
    }
    LaunchedEffect(Unit) {
        if (!hasLocation && !vm.prefs.askedForLocation) {
            vm.prefs.askedForLocation = true
            permissionLauncher.launch(LOCATION_PERMISSIONS)
        }
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, hasLocation) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> if (hasLocation) vm.locationTracker.start()
                Lifecycle.Event.ON_STOP -> vm.locationTracker.stop()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(data.message) {
        data.message?.let {
            snackbar.showSnackbar(it)
            vm.messageShown()
        }
    }

    val openRoute: (my.kl.nightowl.core.Place) -> Unit = { place -> openDirections(context, place, travelMode) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(snackbar) },
        topBar = {
            TopAppBar(
                navigationIcon = { Text("🦉", fontSize = 28.sp, modifier = Modifier.padding(start = 14.dp, end = 4.dp)) },
                title = {
                    Column {
                        Text("KL Night Owl", fontWeight = FontWeight.Bold)
                        Text(
                            "${data.places.size} spots open between midnight & 6 AM",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
                actions = {
                    // The button stays in place and enabled while refreshing (spinner inside it; extra
                    // taps are ignored), so it keeps focus instead of passing it to the search box,
                    // which would pop up the keyboard.
                    IconButton(
                        onClick = {
                            focusManager.clearFocus()
                            vm.refresh()
                        },
                        modifier = Modifier.testTag("refresh"),
                    ) {
                        if (data.refreshing) {
                            CircularProgressIndicator(modifier = Modifier.size(22.dp), strokeWidth = 2.dp)
                        } else {
                            Icon(Icons.Filled.Refresh, contentDescription = "Download the latest data")
                        }
                    }
                    IconButton(onClick = { showAbout = true }, modifier = Modifier.testTag("about")) { Icon(Icons.Filled.Info, contentDescription = "About") }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            )
        },
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainerLow) {
                NavigationBarItem(
                    selected = tab == TAB_LIST,
                    onClick = { tab = TAB_LIST },
                    modifier = Modifier.testTag("tab_list"),
                    icon = { Icon(Icons.Filled.List, contentDescription = null) },
                    label = { Text("List") },
                )
                NavigationBarItem(
                    selected = tab == TAB_MAP,
                    onClick = { tab = TAB_MAP },
                    modifier = Modifier.testTag("tab_map"),
                    icon = { Icon(Icons.Filled.Map, contentDescription = null) },
                    label = { Text("Map") },
                )
            }
        },
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .imePadding(),
        ) {
            FilterBar(
                filters = filters,
                counts = counts,
                showSearch = tab == TAB_LIST,
                onChange = vm::updateFilters,
            )
            Box(Modifier.weight(1f)) {
                when {
                    data.places.isEmpty() && (data.loading || data.refreshing) -> LoadingState()
                    data.places.isEmpty() -> ErrorState(onRetry = { vm.refresh() })
                    tab == TAB_LIST -> PlaceList(
                        places = visible,
                        hoursText = vm.hoursText,
                        todayIndex = now.dayOfWeek.value - 1,
                        refreshing = data.refreshing,
                        footer = dataFooter(data),
                        onRefresh = { vm.refresh() },
                        onShowOnMap = { place ->
                            vm.showOnMap(place)
                            tab = TAB_MAP
                        },
                        onDirections = openRoute,
                        lookupAddress = vm::addressFor,
                    )
                    else -> MapTab(
                        vm = vm,
                        places = visible,
                        selectedId = selectedId,
                        focus = focus,
                        userLocation = userLocation,
                        hasLocationPermission = hasLocation,
                        travelMode = travelMode,
                        onRequestLocation = { permissionLauncher.launch(LOCATION_PERMISSIONS) },
                        onDirections = openRoute,
                    )
                }
            }
        }
    }

    if (showAbout) AboutDialog(data, onDismiss = { showAbout = false })
}

@Composable
private fun FilterBar(
    filters: Filters,
    counts: Map<Category?, Int>,
    showSearch: Boolean,
    onChange: ((Filters) -> Filters) -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        if (showSearch) {
            OutlinedTextField(
                value = filters.query,
                onValueChange = { q -> onChange { it.copy(query = q) } },
                placeholder = { Text("Search a name, type or street") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                trailingIcon = {
                    if (filters.query.isNotEmpty()) {
                        IconButton(onClick = { onChange { it.copy(query = "") } }) {
                            Icon(Icons.Filled.Close, contentDescription = "Clear search")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .testTag("search"),
            )
        }
        LazyRow(
            modifier = Modifier.testTag("category_row"),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item(key = "all") {
                FilterChip(
                    selected = filters.category == null,
                    onClick = { onChange { it.copy(category = null) } },
                    modifier = Modifier.testTag("chip_ALL"),
                    label = { Text("🌙 All  ${counts[null] ?: 0}") },
                )
            }
            val shown = Category.entries.filter { (counts[it] ?: 0) > 0 || filters.category == it }
            items(shown, key = { it.name }) { category ->
                FilterChip(
                    selected = filters.category == category,
                    onClick = { onChange { it.copy(category = if (it.category == category) null else category) } },
                    modifier = Modifier.testTag("chip_${category.name}"),
                    label = { Text("${category.emoji} ${category.shortLabel}  ${counts[category] ?: 0}") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = category.color.copy(alpha = 0.25f),
                        selectedLabelColor = MaterialTheme.colorScheme.onSurface,
                    ),
                )
            }
        }
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            FilterChip(
                selected = filters.openNow,
                onClick = { onChange { it.copy(openNow = !it.openNow) } },
                modifier = Modifier.testTag("open_now"),
                label = { Text("Open now") },
                leadingIcon = if (filters.openNow) {
                    { Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                } else null,
            )
            FilterChip(
                selected = filters.allNight,
                onClick = { onChange { it.copy(allNight = !it.allNight) } },
                modifier = Modifier.testTag("all_night"),
                label = { Text("All night") },
                leadingIcon = if (filters.allNight) {
                    { Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                } else null,
            )
            Spacer(Modifier.weight(1f))
            SortMenu(filters.sort) { mode -> onChange { it.copy(sort = mode) } }
        }
    }
}

@Composable
private fun SortMenu(current: SortMode, onPick: (SortMode) -> Unit) {
    var open by remember { mutableStateOf(false) }
    Box {
        AssistChip(
            onClick = { open = true },
            modifier = Modifier.testTag("sort"),
            label = { Text(current.label) },
            leadingIcon = { Icon(Icons.Filled.SwapVert, contentDescription = null, modifier = Modifier.size(16.dp)) },
        )
        DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
            SortMode.entries.forEach { mode ->
                DropdownMenuItem(
                    text = { Text(mode.label) },
                    onClick = {
                        onPick(mode)
                        open = false
                    },
                )
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
        Spacer(Modifier.height(16.dp))
        Text(
            "Downloading Kuala Lumpur's night spots from OpenStreetMap…\nThis can take up to a minute the first time.",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ErrorState(onRetry: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("📡", fontSize = 48.sp)
        Spacer(Modifier.height(12.dp))
        Text(
            "No data yet. Connect to the internet and tap Try again.",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = onRetry) { Text("Try again") }
    }
}

private fun formatSnapshot(iso: String?): String? = iso?.let {
    runCatching {
        DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a", Locale.ENGLISH).withZone(KL_ZONE).format(Instant.parse(it))
    }.getOrNull()
}

private fun dataFooter(data: DataState): String {
    val source = when (data.source) {
        PlacesRepository.Source.LIVE -> "downloaded just now"
        PlacesRepository.Source.SAVED -> "saved on this phone"
        PlacesRepository.Source.BUNDLED -> "included with the app"
        null -> ""
    }
    val date = formatSnapshot(data.snapshotIso)?.let { "Map data from $it" } ?: "Map data"
    return "$date ($source). Pull down to update.\nOpening hours come from OpenStreetMap volunteers and can be out of date."
}

@Composable
private fun AboutDialog(data: DataState, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { TextButton(onClick = onDismiss) { Text("Close") } },
        title = { Text("🦉 KL Night Owl") },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Text("Shops, restaurants, konbini and more in Kuala Lumpur that are open at some point between midnight and 6 AM.")
                Spacer(Modifier.height(10.dp))
                Text("How to use", fontWeight = FontWeight.Bold)
                Text("• List: tap a card to see the full week's hours. \"Go\" opens Google Maps with the route.")
                Text("• Map: tap a dot, then \"Start route in Google Maps\". Numbered bubbles are groups: tap to zoom in.")
                Text("• Chips at the top filter by category, \"Open now\" or \"All night\" (open the whole 12–6 AM).")
                Spacer(Modifier.height(10.dp))
                Text("Where the data comes from", fontWeight = FontWeight.Bold)
                Text(
                    "Places and hours: © OpenStreetMap contributors (ODbL). " +
                        (formatSnapshot(data.snapshotIso)?.let { "Current data: $it. " } ?: "") +
                        "Tap ⟳ to download the latest. Chains like 7-Eleven, FamilyMart, KK Mart and petrol stations " +
                        "with no hours on the map are shown as \"usually 24h\".",
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    if (USES_GOOGLE_MAP_TILES) "Map: Google Maps." else "Map: © OpenStreetMap contributors.",
                )
                Spacer(Modifier.height(6.dp))
                Text("Version ${BuildConfig.VERSION_NAME}", style = MaterialTheme.typography.labelSmall)
            }
        },
    )
}
