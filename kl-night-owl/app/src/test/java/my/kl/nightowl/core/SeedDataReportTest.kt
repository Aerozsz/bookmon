package my.kl.nightowl.core

import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * Parses the bundled OpenStreetMap snapshot (if present) and prints what the app will show.
 * Runs in CI right after fresh data is downloaded, so the build log doubles as a data report.
 */
class SeedDataReportTest {

    private fun seedFile(): File? =
        listOfNotNull(System.getProperty("seedFile"), "src/main/assets/overpass_seed.json", "app/src/main/assets/overpass_seed.json")
            .map(::File)
            .firstOrNull { it.isFile }

    @Test fun report() {
        val file = seedFile() ?: return println("No bundled seed data yet; skipping report.")
        val result = OsmParser.parse(file.readText())
        println("=== KL Night Owl data report ===")
        println("Snapshot: ${result.snapshot}   OSM features read: ${result.elementCount}   night places: ${result.places.size}")
        for (c in Category.entries) {
            val inCat = result.places.filter { it.category == c }
            println("  %-32s %4d  (%d with published hours)".format(c.label, inCat.size, inCat.count { it.hoursVerified }))
        }
        println("Open 24h: ${result.places.count { it.schedule.isAlwaysOpen }}")
        println("Unreadable opening_hours values (${result.unreadableHours.size}):")
        result.unreadableHours.groupingBy { it }.eachCount().entries.sortedByDescending { it.value }.take(40)
            .forEach { println("  ${it.value}x  ${it.key}") }
        val text = HoursText(use24h = false)
        println("Sample:")
        result.places.shuffled(kotlin.random.Random(7)).take(25).forEach { p ->
            println("  [${p.category.shortLabel}] ${p.name} — ${p.kind} — ${p.openingHoursRaw ?: "(chain, assumed 24h)"} — Sat night: ${text.night(p.schedule.nightCoverage(6))}")
        }
        assertTrue("seed data produced no night places", result.places.isNotEmpty())
    }
}
