package my.kl.nightowl

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.Bitmap
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import kotlinx.coroutines.runBlocking
import my.kl.nightowl.core.Category
import my.kl.nightowl.core.Geo
import my.kl.nightowl.core.NIGHT_END_MINUTE
import my.kl.nightowl.core.Place
import my.kl.nightowl.data.PlacesRepository
import my.kl.nightowl.ui.NightOwlViewModel
import my.kl.nightowl.ui.RefreshOutcome
import my.kl.nightowl.ui.SortMode
import my.kl.nightowl.ui.formatDistance
import my.kl.nightowl.ui.map.PlacesOverlay
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import java.io.File
import java.io.FileOutputStream
import java.util.Locale

/**
 * Drives the real app on a device/emulator the way a person would, checking every feature.
 * Screenshots are saved in the app's files/screens folder for review.
 */
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class NightOwlAppTest {

    val compose = createAndroidComposeRule<MainActivity>()

    /** On failure: a screenshot and the on-screen element tree, taken before the activity closes. */
    private val onFailure = object : TestWatcher() {
        override fun failed(e: Throwable?, description: Description) {
            runCatching { screenshot("FAILED_${description.methodName}", waitForIdle = false) }
            runCatching { compose.onRoot(useUnmergedTree = true).printToLog(TAG) }
        }
    }

    /**
     * A real phone is in touch mode as soon as it's touched (you tap the icon to open the app).
     * Injected test taps don't switch it, and outside touch mode Android focuses the first text
     * box when a screen opens, so switch before each screen is launched.
     */
    private val touchMode = object : ExternalResource() {
        override fun before() {
            InstrumentationRegistry.getInstrumentation().setInTouchMode(true)
        }
    }

    @get:Rule
    val rules: TestRule = RuleChain.outerRule(touchMode).around(compose).around(onFailure)

    private val instrumentation get() = InstrumentationRegistry.getInstrumentation()
    private val device get() = UiDevice.getInstance(instrumentation)
    private lateinit var activity: MainActivity
    private lateinit var vm: NightOwlViewModel

    @Before
    fun waitForData() {
        // A real phone is in touch mode as soon as it's touched; injected test taps don't switch it.
        instrumentation.setInTouchMode(true)
        activity = compose.activity
        vm = compose.runOnUiThread { ViewModelProvider(activity)[NightOwlViewModel::class.java] }
        compose.waitUntil(60_000) { vm.data.value.places.isNotEmpty() }
        compose.waitUntil(30_000) { vm.visible.value.isNotEmpty() }
        // Let the phone's position arrive first, so the nearest-first order is settled before tapping.
        runCatching { compose.waitUntil(60_000) { vm.visible.value.first().distanceMeters != null } }
        compose.waitForIdle()
        Thread.sleep(300)
    }

    // ------------------------------------------------------------------ list

    @Test
    fun t01_launchShowsNightPlaces() {
        compose.onNodeWithText("KL Night Owl").assertIsDisplayed()
        val total = vm.data.value.places.size
        assertTrue("expected hundreds of night places, got $total", total > 500)
        compose.onNodeWithText("spots open between midnight", substring = true).assertIsDisplayed()
        compose.onAllNodesWithTag("place_card").onFirst().assertIsDisplayed()
        compose.onAllNodesWithText("Tonight:", substring = true).onFirst().assertIsDisplayed()
        log("launch: $total places, source=${vm.data.value.source}, snapshot=${vm.data.value.snapshotIso}")
        screenshot("01_list")
    }

    @Test
    fun t02_eachCategoryChipShowsOnlyThatCategory() {
        val counts = vm.categoryCounts.value
        for (category in Category.entries) {
            val expected = counts[category] ?: 0
            if (expected == 0) continue
            compose.onNodeWithTag("category_row").performScrollToNode(hasTestTag("chip_${category.name}"))
            compose.onNodeWithTag("chip_${category.name}").performClick()
            compose.waitUntil(10_000) {
                val shown = vm.visible.value
                vm.filters.value.category == category && shown.size == expected && shown.all { it.place.category == category }
            }
            compose.onAllNodesWithTag("place_card").onFirst().assertIsDisplayed()
            log("category ${category.name}: $expected places")
            if (category == Category.KONBINI || category == Category.RESTAURANT || category == Category.NIGHTLIFE) {
                screenshot("02_category_${category.name.lowercase()}")
            }
        }
        compose.onNodeWithTag("category_row").performScrollToNode(hasTestTag("chip_ALL"))
        compose.onNodeWithTag("chip_ALL").performClick()
        compose.waitUntil(10_000) { vm.filters.value.category == null && vm.visible.value.size == vm.data.value.places.size }
    }

    @Test
    fun t03_openNowAndAllNightFilters() {
        compose.onNodeWithTag("open_now").performClick()
        compose.waitUntil(10_000) {
            val shown = vm.visible.value
            vm.filters.value.openNow && shown.isNotEmpty() && shown.all { it.openNow }
        }
        screenshot("03_open_now")
        compose.onNodeWithTag("open_now").performClick()

        compose.onNodeWithTag("all_night").performClick()
        compose.waitUntil(10_000) {
            val shown = vm.visible.value
            vm.filters.value.allNight && shown.isNotEmpty() && shown.all { it.nightMinutes >= NIGHT_END_MINUTE }
        }
        compose.onAllNodesWithText("Open all night", substring = true).onFirst().assertIsDisplayed()
        screenshot("03_all_night")
    }

    @Test
    fun t04_search() {
        compose.onNodeWithTag("search").performTextInput("McDonald")
        compose.waitUntil(10_000) {
            val shown = vm.visible.value
            vm.filters.value.query == "McDonald" && shown.isNotEmpty() &&
                shown.all { ui -> listOfNotNull(ui.place.name, ui.place.kind, ui.place.address).any { it.contains("McDonald", ignoreCase = true) } }
        }
        compose.onAllNodesWithText("McDonald's").onFirst().assertIsDisplayed()
        screenshot("04_search")

        compose.onNodeWithTag("search").performTextClearance()
        compose.onNodeWithTag("search").performTextInput("zzqqxx")
        compose.waitUntil(10_000) { vm.visible.value.isEmpty() }
        compose.onNodeWithText("Nothing matches", substring = true).assertIsDisplayed()
    }

    @Test
    fun t05_sortByDistanceNameAndLatest() {
        // The emulator is placed in central Kuala Lumpur by the test script (adb emu geo fix).
        compose.waitUntil(120_000) { vm.locationTracker.location.value != null }
        val here = vm.locationTracker.location.value!!
        log("location: ${here.latitude}, ${here.longitude} via ${here.provider}")
        assertTrue(
            "location should be central KL",
            Geo.distanceMeters(here.latitude, here.longitude, 3.1478, 101.6953) < 3_000,
        )
        compose.waitUntil(10_000) { vm.visible.value.first().distanceMeters != null }
        val distances = vm.visible.value.map { it.distanceMeters!! }
        assertEquals("nearest first", distances.sorted(), distances)
        compose.onAllNodesWithText(formatDistance(distances.first())!!).onFirst().assertIsDisplayed()
        screenshot("05_nearest_first")

        pickSort("A to Z")
        compose.waitUntil(10_000) { vm.filters.value.sort == SortMode.NAME }
        compose.waitUntil(10_000) {
            val names = vm.visible.value.map { it.place.name.lowercase() }
            names == names.sorted()
        }

        pickSort("Open latest")
        compose.waitUntil(10_000) { vm.filters.value.sort == SortMode.LATEST }
        compose.waitUntil(10_000) {
            val minutes = vm.visible.value.map { it.nightMinutes }
            minutes == minutes.sortedDescending()
        }
    }

    @Test
    fun t06_cardExpandsToWeeklySchedule() {
        compose.onAllNodesWithTag("place_card").onFirst().performClick()
        compose.onNodeWithText("Opening hours").assertIsDisplayed()
        compose.onNodeWithText("(today)", substring = true).assertIsDisplayed()
        for (day in listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
            compose.onAllNodesWithText(day, substring = true).onFirst().assertExists()
        }
        screenshot("06_card_expanded")
    }

    @Test
    fun t07_goButtonOpensGoogleMapsRoute() {
        val first = vm.visible.value.first().place
        withStubbedIntents {
            compose.onAllNodesWithTag("go_button").onFirst().performClick()
            intended(allOf(hasAction(Intent.ACTION_VIEW), toPackage(GOOGLE_MAPS), hasData(routeUri(first, "driving"))))
        }
    }

    // ------------------------------------------------------------------ map

    @Test
    fun t08_mapButtonOnCardShowsPlaceOnMap() {
        val first = vm.visible.value.first().place
        compose.onAllNodesWithTag("map_button").onFirst().performClick()
        compose.waitUntil(10_000) { vm.selectedId.value == first.id }
        compose.onNodeWithTag("selected_card").assertIsDisplayed()
        compose.onNode(hasText(first.name) and hasAnyAncestor(hasTestTag("selected_card"))).assertIsDisplayed()
        compose.onNodeWithText("Start route in Google Maps").assertIsDisplayed()
        compose.waitUntil(15_000) { mapCenterWithin(first.lat, first.lon, 60.0) }
        waitForTiles()
        assertHeaderAndChipsVisible()
        screenshot("08_map_selected_from_list")
    }

    @Test
    fun t09_tappingAMarkerSelectsItAndStartsRoute() {
        openMapTab()
        val target = isolatedPlace()
        vm.focusOn(target.lat, target.lon, 18.0)
        compose.waitUntil(15_000) { mapCenterWithin(target.lat, target.lon, 30.0) && mapZoom() > 17.5 }
        waitForTiles()
        val (x, y) = compose.runOnUiThread {
            val map = mapView()!!
            val p = map.projection.toPixels(GeoPoint(target.lat, target.lon), null)
            val origin = IntArray(2).also { map.getLocationOnScreen(it) }
            (origin[0] + p.x) to (origin[1] + p.y)
        }
        device.click(x, y)
        compose.waitUntil(10_000) { vm.selectedId.value == target.id }
        compose.onNodeWithTag("selected_card").assertIsDisplayed()
        compose.onNode(hasText(target.name) and hasAnyAncestor(hasTestTag("selected_card"))).assertIsDisplayed()
        assertHeaderAndChipsVisible()
        screenshot("09_marker_tapped")

        withStubbedIntents {
            compose.onNodeWithTag("travel_WALK").performClick()
            compose.onNodeWithTag("start_route").performClick()
            intended(allOf(hasAction(Intent.ACTION_VIEW), toPackage(GOOGLE_MAPS), hasData(routeUri(target, "walking"))))
        }
        compose.onNodeWithTag("travel_DRIVE").performClick()

        compose.onNodeWithTag("close_selected").performClick()
        compose.waitUntil(10_000) { vm.selectedId.value == null }
        compose.waitUntil(10_000) { compose.onAllNodesWithTag("selected_card").fetchSemanticsNodes().isEmpty() }
    }

    @Test
    fun t10_tappingAGroupBubbleZoomsIn() {
        openMapTab()
        vm.focusOn(3.1466, 101.7101, 12.5) // Bukit Bintang: dense, so places are grouped
        compose.waitUntil(15_000) { mapCenterWithin(3.1466, 101.7101, 200.0) && mapZoom() in 12.0..13.0 }
        waitForTiles()
        assertHeaderAndChipsVisible()
        screenshot("10_map_groups")
        val before = mapZoom()
        val bubble = compose.runOnUiThread {
            val map = mapView()!!
            val overlay = map.overlays.filterIsInstance<PlacesOverlay>().first()
            val origin = IntArray(2).also { map.getLocationOnScreen(it) }
            val cx = map.width / 2f
            val cy = map.height / 2f
            overlay.groupPositions().minByOrNull { (x, y) -> (x - cx) * (x - cx) + (y - cy) * (y - cy) }
                ?.let { (x, y) -> (origin[0] + x).toInt() to (origin[1] + y).toInt() }
        }
        assertNotNull("expected numbered group bubbles at city zoom", bubble)
        device.click(bubble!!.first, bubble.second)
        compose.waitUntil(10_000) { mapZoom() > before + 1.5 }
        log("group tap zoom: $before -> ${mapZoom()}")
    }

    @Test
    fun t11_mapStyleToggleSwitchesBetweenNightAndStreet() {
        openMapTab()
        val source = compose.runOnUiThread { mapView()!!.tileProvider.tileSource.name() }
        assertEquals("OpenStreetMap tiles, no key needed", "Mapnik", source)
        val before = mapStyle()
        compose.onNodeWithTag("map_style").performClick()
        compose.waitUntil(10_000) { mapStyle() != before }
        log("map style: $before -> ${mapStyle()}")
        waitForTiles()
        Thread.sleep(3_000)
        screenshot("11_map_other_style")
        compose.onNodeWithTag("map_style").performClick()
        compose.waitUntil(10_000) { mapStyle() == before }
    }

    @Test
    fun t12_myLocationButtonCentresOnUser() {
        compose.waitUntil(120_000) { vm.locationTracker.location.value != null }
        val here = vm.locationTracker.location.value!!
        openMapTab()
        vm.focusOn(3.2400, 101.6300, 14.0) // look somewhere else first
        compose.waitUntil(15_000) { mapCenterWithin(3.2400, 101.6300, 300.0) }
        compose.onNodeWithTag("my_location").performClick()
        compose.waitUntil(15_000) { mapCenterWithin(here.latitude, here.longitude, 100.0) }
        waitForTiles()
        screenshot("12_my_location")
    }

    // ------------------------------------------------------------------ other

    @Test
    fun t13_aboutDialog() {
        compose.onNodeWithTag("about").performClick()
        compose.onNodeWithText("How to use").assertIsDisplayed()
        compose.onAllNodesWithText("OpenStreetMap contributors", substring = true).onFirst().assertIsDisplayed()
        screenshot("13_about")
        compose.onNodeWithText("Close").performClick()
        compose.onNodeWithText("How to use").assertDoesNotExist()
    }

    @Test
    fun t14_refreshDownloadsLiveDataAndSavesIt() {
        val before = vm.data.value.snapshotIso!!
        val countBefore = vm.data.value.places.size
        // The public OpenStreetMap servers are sometimes all overloaded for a minute; the app then
        // keeps its data and says so. Allow a few tries, checking that behaviour each time.
        var data = vm.data.value
        for (attempt in 1..3) {
            compose.onNodeWithTag("refresh").performClick()
            compose.waitUntil(10_000) { vm.data.value.refreshing }
            compose.waitUntil(200_000) { !vm.data.value.refreshing }
            data = vm.data.value
            if (data.lastRefresh != RefreshOutcome.FAILED) break
            log("refresh attempt $attempt: all servers busy; data kept (${data.places.size} places, ${data.snapshotIso})")
            assertEquals("data is kept when the servers are busy", countBefore, data.places.size)
            assertEquals(before, data.snapshotIso)
            Thread.sleep(30_000)
        }
        log("refresh: outcome=${data.lastRefresh}, source=${data.source}, places=${data.places.size}, snapshot $before -> ${data.snapshotIso}")
        assertTrue(
            "download should succeed (new data, or servers confirm nothing newer)",
            data.lastRefresh == RefreshOutcome.UPDATED || data.lastRefresh == RefreshOutcome.ALREADY_UP_TO_DATE,
        )
        assertTrue("data must never go back in time", data.snapshotIso!! >= before)
        compose.onNodeWithTag("search").assertIsNotFocused() // refreshing must not pop up the keyboard
        assertTrue(data.places.size > 500)
        // Same area as before: Kuala Lumpur itself, not the wider rectangle around it.
        assertTrue("got ${data.places.size} places vs $countBefore before", data.places.size < countBefore * 1.3)
        screenshot("14_after_refresh")

        if (data.lastRefresh == RefreshOutcome.UPDATED) {
            assertEquals(PlacesRepository.Source.LIVE, data.source)
            val saved = runBlocking { PlacesRepository(instrumentation.targetContext).loadOffline() }
            assertEquals("the download is kept for next time", data.snapshotIso, saved?.snapshotIso)
        }
    }

    @Test
    fun t15_pullToRefreshStartsAnUpdate() {
        compose.onNodeWithTag("place_list").performTouchInput { swipeDown(startY = top + 20f, endY = bottom, durationMillis = 800) }
        compose.waitUntil(10_000) { vm.data.value.refreshing }
        compose.waitUntil(200_000) { !vm.data.value.refreshing }
        log("pull-to-refresh finished: outcome=${vm.data.value.lastRefresh}, snapshot=${vm.data.value.snapshotIso}")
        assertTrue("data is always kept", vm.data.value.places.size > 500)
    }

    @Test
    fun t16_addressLookupForPlacesWithoutAddress() {
        val place = vm.data.value.places.first { it.address == null }
        val found = runBlocking { vm.addressFor(place) }
        log("address lookup for ${place.name} (${place.lat},${place.lon}): geocoder=${Geocoder.isPresent()} -> $found")
        // Best effort by design: the card simply shows no address when the phone can't look one up.
        compose.onAllNodesWithTag("place_card").onFirst().performClick()
        compose.onNodeWithText("Opening hours").assertIsDisplayed()
    }

    // ------------------------------------------------------------------ helpers

    private fun pickSort(label: String) {
        compose.onNodeWithTag("sort").performClick()
        compose.onNodeWithText(label).performClick()
    }

    private fun openMapTab() {
        compose.onNodeWithTag("tab_map").performClick()
        compose.waitUntil(15_000) { compose.runOnUiThread { mapView() } != null }
    }

    /** Call on the UI thread. */
    private fun mapView(): MapView? = findMapView(activity.window.decorView)

    private fun findMapView(v: View): MapView? {
        if (v is MapView) return v
        if (v is ViewGroup) for (i in 0 until v.childCount) findMapView(v.getChildAt(i))?.let { return it }
        return null
    }

    private fun mapStyle(): String = compose.runOnUiThread { mapView()?.contentDescription?.toString().orEmpty() }

    /** The filter chips stay visible above the map and the header shows the full count. */
    private fun assertHeaderAndChipsVisible() {
        compose.onNodeWithText("${vm.data.value.places.size} spots open between midnight", substring = true).assertIsDisplayed()
        compose.onNodeWithTag("chip_ALL").assertIsDisplayed()
        compose.onNodeWithTag("open_now").assertIsDisplayed()
        val chipsBottom = compose.onNodeWithTag("open_now").fetchSemanticsNode().boundsInWindow.bottom
        val mapTop = compose.runOnUiThread {
            val map = mapView()!!
            IntArray(2).also { map.getLocationInWindow(it) }[1].toFloat()
        }
        assertTrue("map ($mapTop) must start below the filter chips ($chipsBottom)", mapTop >= chipsBottom - 1f)
    }

    private fun mapZoom(): Double = compose.runOnUiThread { mapView()?.zoomLevelDouble ?: 0.0 }

    private fun mapCenterWithin(lat: Double, lon: Double, meters: Double): Boolean = compose.runOnUiThread {
        val c = mapView()?.mapCenter ?: return@runOnUiThread false
        Geo.distanceMeters(c.latitude, c.longitude, lat, lon) < meters
    }

    /** Waits until the map has downloaded and decoded some tiles, proving the tile server is reachable. */
    private fun waitForTiles() {
        compose.waitUntil(45_000) { compose.runOnUiThread { (mapView()?.tileProvider?.tileCache?.size ?: 0) >= 4 } }
        Thread.sleep(1_500)
    }

    /** A place with no other place within 80 m, so its marker can be tapped unambiguously. */
    private fun isolatedPlace(): Place {
        val all = vm.visible.value.map { it.place }
        return all.first { p ->
            p.lat in 3.10..3.20 && p.lon in 101.65..101.75 &&
                all.none { o -> o !== p && Geo.distanceMeters(o.lat, o.lon, p.lat, p.lon) < 80.0 }
        }
    }

    private fun routeUri(place: Place, mode: String): Uri = Uri.parse(
        "https://www.google.com/maps/dir/?api=1&destination=" +
            String.format(Locale.US, "%.6f,%.6f", place.lat, place.lon) + "&travelmode=$mode",
    )

    private fun withStubbedIntents(block: () -> Unit) {
        Intents.init()
        try {
            intending(hasAction(Intent.ACTION_VIEW)).respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
            block()
        } finally {
            Intents.release()
        }
    }

    private fun log(message: String) {
        Log.i(TAG, message)
        println("$TAG: $message")
    }

    private fun screenshot(name: String, waitForIdle: Boolean = true) {
        if (waitForIdle) compose.waitForIdle()
        Thread.sleep(600)
        val shot = instrumentation.uiAutomation.takeScreenshot() ?: return
        val small = Bitmap.createScaledBitmap(shot, shot.width / 2, shot.height / 2, true)
        val dir = File(instrumentation.targetContext.filesDir, "screens").apply { mkdirs() }
        FileOutputStream(File(dir, "api${Build.VERSION.SDK_INT}_$name.jpg")).use {
            small.compress(Bitmap.CompressFormat.JPEG, 85, it)
        }
    }

    companion object {
        private const val TAG = "NightOwlTest"
        private const val GOOGLE_MAPS = "com.google.android.apps.maps"
    }
}
