package my.kl.nightowl.core

/** Turns schedules into short, human sentences. Kept free of Android so it can be unit tested. */
class HoursText(private val use24h: Boolean) {

    fun time(minute: Int): String {
        val m = Math.floorMod(minute, MINUTES_PER_DAY)
        val h = m / 60
        val mm = (m % 60).toString().padStart(2, '0')
        if (use24h) return h.toString().padStart(2, '0') + ":" + mm
        return when {
            m == 0 -> "midnight"
            h == 0 -> "12:$mm AM"
            h < 12 -> "$h:$mm AM"
            h == 12 -> "12:$mm PM"
            else -> "${h - 12}:$mm PM"
        }
    }

    private fun range(span: Interval) = "${time(span.start)} – ${time(span.end)}"

    /** One line of the weekly table: "7:00 AM – 3:00 AM", "Open 24 hours" or "Closed". */
    fun day(spans: List<Interval>): String = when {
        spans.isEmpty() -> "Closed"
        spans.any { it.start <= 0 && it.end >= MINUTES_PER_DAY } -> "Open 24 hours"
        else -> spans.joinToString(", ") { range(it) }
    }

    /** What happens between midnight and 6 AM, from [WeeklySchedule.nightCoverage]. */
    fun night(coverage: List<Interval>): String = when {
        coverage.isEmpty() -> "Closed after midnight"
        coverage.size == 1 && coverage[0].start == 0 && coverage[0].end >= NIGHT_END_MINUTE -> "Open all night"
        coverage.size == 1 && coverage[0].start == 0 -> "Open until ${time(coverage[0].end)}"
        coverage.size == 1 && coverage[0].end >= NIGHT_END_MINUTE -> "Opens at ${time(coverage[0].start)}"
        else -> coverage.joinToString(", ") { range(it) }
    }

    companion object {
        val DAY_NAMES = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    }
}
