package my.kl.nightowl.ui

import android.app.Application
import android.location.Location
import android.text.format.DateFormat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.kl.nightowl.core.Category
import my.kl.nightowl.core.Geo
import my.kl.nightowl.core.HoursText
import my.kl.nightowl.core.MINUTES_PER_DAY
import my.kl.nightowl.core.NIGHT_END_MINUTE
import my.kl.nightowl.core.Place
import my.kl.nightowl.data.LocationTracker
import my.kl.nightowl.data.PlacesRepository
import my.kl.nightowl.data.Prefs
import my.kl.nightowl.data.TravelMode
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

val KL_ZONE: ZoneId = ZoneId.of("Asia/Kuala_Lumpur")

enum class SortMode(val label: String) {
    NEAREST("Nearest first"),
    LATEST("Open latest"),
    NAME("A to Z"),
}

/** A place plus everything the screens show about it right now. */
data class PlaceUi(
    val place: Place,
    val openNow: Boolean,
    /** "Open 24 hours", "Open until 3:00 AM", "Opens 6:00 PM". */
    val statusText: String,
    /** What happens between midnight and 6 AM tonight: "Open all night", "Open until 2:00 AM". */
    val tonightText: String,
    /** Minutes open between midnight and 6 AM tonight (360 = the whole night). */
    val nightMinutes: Int,
    val distanceMeters: Double?,
)

data class Filters(
    val category: Category? = null,
    val query: String = "",
    val openNow: Boolean = false,
    val allNight: Boolean = false,
    val sort: SortMode = SortMode.NEAREST,
)

data class DataState(
    val places: List<Place> = emptyList(),
    val snapshotIso: String? = null,
    val source: PlacesRepository.Source? = null,
    val loading: Boolean = true,
    val refreshing: Boolean = false,
    val message: String? = null,
)

/** Asks a map to fly to a place. [nonce] makes repeated requests for the same place distinct. */
data class FocusRequest(val lat: Double, val lon: Double, val zoom: Double, val nonce: Long = System.nanoTime())

class NightOwlViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = PlacesRepository(app)
    val prefs = Prefs(app)
    val locationTracker = LocationTracker(app)
    val hoursText = HoursText(DateFormat.is24HourFormat(app))

    private val _data = MutableStateFlow(DataState())
    val data: StateFlow<DataState> = _data.asStateFlow()

    private val _filters = MutableStateFlow(Filters())
    val filters: StateFlow<Filters> = _filters.asStateFlow()

    private val _selectedId = MutableStateFlow<String?>(null)
    val selectedId: StateFlow<String?> = _selectedId.asStateFlow()

    private val _focus = MutableStateFlow<FocusRequest?>(null)
    val focus: StateFlow<FocusRequest?> = _focus.asStateFlow()

    private val _travelMode = MutableStateFlow(prefs.travelMode)
    val travelMode: StateFlow<TravelMode> = _travelMode.asStateFlow()

    /** Where the map was last looking, so switching tabs doesn't reset it. */
    var cameraLat = 3.1478
    var cameraLon = 101.6953
    var cameraZoom = 13.0

    private val clock: Flow<ZonedDateTime> = flow {
        while (true) {
            val now = ZonedDateTime.now(KL_ZONE)
            emit(now)
            delay(60_000L - now.second * 1_000L - now.nano / 1_000_000L)
        }
    }
    val now: StateFlow<ZonedDateTime> =
        clock.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ZonedDateTime.now(KL_ZONE))

    private val decorated: StateFlow<List<PlaceUi>> =
        combine(_data, now, locationTracker.location) { data, time, location -> decorate(data.places, time, location) }
            .flowOn(Dispatchers.Default)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    /** What the list and the map show, after filters and sorting. */
    val visible: StateFlow<List<PlaceUi>> =
        combine(decorated, _filters) { all, f -> sort(all.filter { matches(it, f, ignoreCategory = false) }, f.sort) }
            .flowOn(Dispatchers.Default)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    /** How many places each category chip would show (null key = all). */
    val categoryCounts: StateFlow<Map<Category?, Int>> =
        combine(decorated, _filters) { all, f ->
            val matching = all.filter { matches(it, f, ignoreCategory = true) }
            val counts = mutableMapOf<Category?, Int>(null to matching.size)
            counts.putAll(matching.groupingBy { it.place.category }.eachCount())
            counts.toMap()
        }
            .flowOn(Dispatchers.Default)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyMap())

    init {
        viewModelScope.launch {
            val offline = repository.loadOffline()
            if (offline != null) {
                _data.value = DataState(offline.places, offline.snapshotIso, offline.source, loading = false)
            }
            if (offline == null || isStale(offline)) refresh(userAsked = false)
        }
    }

    fun refresh(userAsked: Boolean = true) {
        if (_data.value.refreshing) return
        _data.update { it.copy(refreshing = true, message = null) }
        viewModelScope.launch {
            try {
                val snapshot = repository.refresh()
                _data.value = DataState(snapshot.places, snapshot.snapshotIso, snapshot.source, loading = false)
                if (userAsked) _data.update { it.copy(message = "Updated: ${snapshot.places.size} night spots") }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _data.update {
                    it.copy(
                        loading = false,
                        refreshing = false,
                        message = if (userAsked || it.places.isEmpty()) {
                            "Couldn't reach OpenStreetMap. Check your internet connection and try again."
                        } else null,
                    )
                }
            }
        }
    }

    fun messageShown() = _data.update { it.copy(message = null) }

    fun updateFilters(transform: (Filters) -> Filters) = _filters.update(transform)

    fun select(id: String?) {
        _selectedId.value = id
    }

    fun showOnMap(place: Place) {
        _selectedId.value = place.id
        _focus.value = FocusRequest(place.lat, place.lon, 17.0)
    }

    fun focusOn(lat: Double, lon: Double, zoom: Double = 16.0) {
        _focus.value = FocusRequest(lat, lon, zoom)
    }

    fun focusConsumed() {
        _focus.value = null
    }

    fun setTravelMode(mode: TravelMode) {
        _travelMode.value = mode
        prefs.travelMode = mode
    }

    // ------------------------------------------------------------------ helpers

    private fun isStale(snapshot: PlacesRepository.Snapshot): Boolean {
        val threeDays = 3L * 24 * 3600 * 1000
        val madeAt = when (snapshot.source) {
            PlacesRepository.Source.SAVED -> snapshot.savedAtMillis
            else -> snapshot.snapshotIso?.let { runCatching { Instant.parse(it).toEpochMilli() }.getOrNull() } ?: 0L
        }
        return System.currentTimeMillis() - madeAt > threeDays
    }

    private fun decorate(places: List<Place>, time: ZonedDateTime, location: Location?): List<PlaceUi> {
        val day = time.dayOfWeek.value - 1 // Monday = 0
        val minuteOfDay = time.hour * 60 + time.minute
        val weekMinute = day * MINUTES_PER_DAY + minuteOfDay
        // Before 6 AM we are in tonight already; after that, "tonight" is the coming night.
        val nightDay = if (minuteOfDay < NIGHT_END_MINUTE) day else (day + 1) % 7
        return places.map { place ->
            val schedule = place.schedule
            val open = schedule.isOpenAt(weekMinute)
            val coverage = schedule.nightCoverage(nightDay)
            val status = when {
                schedule.isAlwaysOpen -> "Open 24 hours"
                else -> {
                    val next = schedule.nextChange(weekMinute)
                    when {
                        next == null -> if (open) "Open" else "Closed"
                        open -> "Open until ${whenText(weekMinute, next)}"
                        else -> "Closed · opens ${whenText(weekMinute, next)}"
                    }
                }
            }
            PlaceUi(
                place = place,
                openNow = open,
                statusText = status,
                tonightText = if (schedule.isAlwaysOpen) "Open all night (24 hours)" else hoursText.night(coverage),
                nightMinutes = coverage.sumOf { it.length },
                distanceMeters = location?.let { Geo.distanceMeters(it.latitude, it.longitude, place.lat, place.lon) },
            )
        }
    }

    private fun whenText(nowWeekMinute: Int, atWeekMinute: Int): String {
        val time = hoursText.time(atWeekMinute)
        if (atWeekMinute - nowWeekMinute < 20 * 60) return time
        val dayName = HoursText.DAY_NAMES[(atWeekMinute / MINUTES_PER_DAY) % 7]
        return "$dayName $time"
    }

    private fun matches(ui: PlaceUi, f: Filters, ignoreCategory: Boolean): Boolean {
        if (!ignoreCategory && f.category != null && ui.place.category != f.category) return false
        if (f.openNow && !ui.openNow) return false
        if (f.allNight && ui.nightMinutes < NIGHT_END_MINUTE) return false
        val q = f.query.trim()
        if (q.isNotEmpty()) {
            val haystack = listOfNotNull(ui.place.name, ui.place.kind, ui.place.address)
            if (haystack.none { it.contains(q, ignoreCase = true) }) return false
        }
        return true
    }

    private fun sort(list: List<PlaceUi>, mode: SortMode): List<PlaceUi> = when (mode) {
        SortMode.NEAREST ->
            if (list.any { it.distanceMeters != null }) list.sortedBy { it.distanceMeters ?: Double.MAX_VALUE }
            else list.sortedWith(compareByDescending<PlaceUi> { it.nightMinutes }.thenBy { it.place.name.lowercase() })
        SortMode.LATEST -> list.sortedWith(
            compareByDescending<PlaceUi> { it.nightMinutes }
                .thenBy { it.distanceMeters ?: Double.MAX_VALUE }
                .thenBy { it.place.name.lowercase() },
        )
        SortMode.NAME -> list.sortedBy { it.place.name.lowercase() }
    }

    override fun onCleared() {
        locationTracker.stop()
    }
}
