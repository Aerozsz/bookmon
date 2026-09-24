package my.kl.nightowl.core

const val MINUTES_PER_DAY = 1440
const val MINUTES_PER_WEEK = 7 * MINUTES_PER_DAY

/** The night this app is about: from midnight until 6 AM. */
const val NIGHT_END_MINUTE = 6 * 60

/**
 * Minutes relative to a day's midnight. [end] may be past 1440 when the span runs
 * past midnight into the next day (OSM "18:00-03:00" becomes 1080..1620).
 */
data class Interval(val start: Int, val end: Int) {
    val length: Int get() = end - start
}

/**
 * Opening hours for a typical week. Index 0 is Monday, 6 is Sunday.
 */
class WeeklySchedule(days: List<List<Interval>>) {

    val days: List<List<Interval>> = days.map { spans -> spans.sortedBy { it.start } }

    /** Merged open spans on a week timeline starting Monday 00:00; Sunday night wraps into Monday. */
    private val timeline: List<Interval> = buildTimeline(this.days)

    val isAlwaysOpen: Boolean =
        timeline.size == 1 && timeline[0].start == 0 && timeline[0].end == MINUTES_PER_WEEK

    val isAlwaysClosed: Boolean get() = timeline.isEmpty()

    fun isOpenAt(weekMinute: Int): Boolean {
        val m = Math.floorMod(weekMinute, MINUTES_PER_WEEK)
        return timeline.any { m >= it.start && m < it.end }
    }

    /**
     * The next week minute at which this place opens or closes, measured from [weekMinute].
     * The result can be beyond [MINUTES_PER_WEEK] (it then falls in the following week).
     * Null when the state never changes (always open or always closed).
     */
    fun nextChange(weekMinute: Int): Int? {
        if (timeline.isEmpty() || isAlwaysOpen) return null
        val m = Math.floorMod(weekMinute, MINUTES_PER_WEEK)
        // Lay two weeks end to end so spans that cross Sunday -> Monday join up.
        val twoWeeks = mergeSorted(timeline + timeline.map { Interval(it.start + MINUTES_PER_WEEK, it.end + MINUTES_PER_WEEK) })
        val containing = twoWeeks.firstOrNull { m >= it.start && m < it.end }
        val next = containing?.end ?: twoWeeks.first { it.start > m }.start
        return next - m + weekMinute
    }

    /** Open spans during the early hours (00:00–06:00) of [day], in minutes after that day's midnight. */
    fun nightCoverage(day: Int): List<Interval> {
        val lo = day * MINUTES_PER_DAY
        val hi = lo + NIGHT_END_MINUTE
        return timeline.mapNotNull { span ->
            val a = maxOf(span.start, lo)
            val b = minOf(span.end, hi)
            if (b > a) Interval(a - lo, b - lo) else null
        }
    }

    /** True when the place is open at some point between midnight and 6 AM on at least one day. */
    val isOpenAtNight: Boolean by lazy { (0..6).any { nightCoverage(it).isNotEmpty() } }

    companion object {
        fun alwaysOpen() = WeeklySchedule(List(7) { listOf(Interval(0, MINUTES_PER_DAY)) })

        private fun buildTimeline(days: List<List<Interval>>): List<Interval> {
            val raw = mutableListOf<Interval>()
            days.forEachIndexed { day, spans ->
                for (span in spans) {
                    if (span.end <= span.start) continue
                    val a = day * MINUTES_PER_DAY + span.start
                    val b = day * MINUTES_PER_DAY + span.end
                    if (b <= MINUTES_PER_WEEK) {
                        raw += Interval(a, b)
                    } else {
                        if (a < MINUTES_PER_WEEK) raw += Interval(a, MINUTES_PER_WEEK)
                        raw += Interval(maxOf(a, MINUTES_PER_WEEK) - MINUTES_PER_WEEK, b - MINUTES_PER_WEEK)
                    }
                }
            }
            return mergeSorted(raw)
        }

        private fun mergeSorted(spans: List<Interval>): List<Interval> {
            val merged = mutableListOf<Interval>()
            for (span in spans.sortedBy { it.start }) {
                val last = merged.lastOrNull()
                if (last != null && span.start <= last.end) {
                    merged[merged.lastIndex] = Interval(last.start, maxOf(last.end, span.end))
                } else {
                    merged += span
                }
            }
            return merged
        }
    }
}

/**
 * Parses the OpenStreetMap `opening_hours` syntax (https://wiki.openstreetmap.org/wiki/Key:opening_hours).
 *
 * Covers what shops in Kuala Lumpur actually use: weekday ranges and lists, times that run past
 * midnight, `24/7`, `off`, several rules separated by `;` or `,`, plus common free-text variants
 * such as "24 hours" or "7am-2am". Public-holiday and date-specific rules are ignored because
 * they don't describe a normal week.
 */
object OpeningHoursParser {

    fun parse(raw: String?): WeeklySchedule? {
        if (raw.isNullOrBlank()) return null
        val text = normalize(raw)
        val days = Array(7) { mutableListOf<Interval>() }
        var applied = false
        for (ruleText in text.split(';', '|').map { it.trim() }.filter { it.isNotEmpty() }) {
            val rules = parseRuleText(tokenize(ruleText)) ?: continue
            for (rule in rules) {
                if (!rule.additional) {
                    for (d in rule.days) days[d].clear()
                }
                for (d in rule.days) {
                    if (rule.off) days[d].clear() else days[d].addAll(rule.spans)
                }
                applied = true
            }
        }
        if (!applied) return null
        return WeeklySchedule(days.map { it.toList() })
    }

    // ---------------------------------------------------------------- normalisation

    private val AM_PM = Regex("""(?i)\b(\d{1,2})(?:[:.](\d{2}))?\s*([ap])\.?\s*m\b\.?""")
    private val TWENTY_FOUR_SEVEN = Regex("""(?i)\b24\s*(?:/|x|\s)\s*7\b""")
    private val TWENTY_FOUR_HOURS = Regex("""(?i)(?:\bopen\s+)?\b24\s*-?\s*(?:hours|hour|hrs|hr|h|jam)\b""")

    internal fun normalize(raw: String): String {
        var s = raw
            .replace('–', '-').replace('—', '-').replace('−', '-').replace('‒', '-')
            .replace('~', '-').replace('：', ':').replace(' ', ' ')
        s = TWENTY_FOUR_SEVEN.replace(s, "24/7")
        s = TWENTY_FOUR_HOURS.replace(s, "00:00-24:00")
        s = AM_PM.replace(s) { m ->
            var h = m.groupValues[1].toInt()
            val min = m.groupValues[2].ifEmpty { "00" }
            val pm = m.groupValues[3].equals("p", ignoreCase = true)
            if (h == 12) h = 0
            if (pm) h += 12
            h.toString().padStart(2, '0') + ":" + min
        }
        s = s.replace(Regex("""(?i)\s+(?:to|until|till)\s+"""), "-")
        s = s.replace(Regex("""(?i)-\s*midnight\b"""), "-24:00")
        s = s.replace(Regex("""(?i)\bmidnight\b"""), "00:00")
        s = s.replace(Regex("""(?i)\bnoon\b"""), "12:00")
        s = s.replace(Regex("""(?i)\bsunrise\b"""), "07:00")
        s = s.replace(Regex("""(?i)\bsunset\b"""), "19:15")
        s = s.replace(Regex("""(?i)\bdawn\b"""), "06:45")
        s = s.replace(Regex("""(?i)\bdusk\b"""), "19:40")
        return s.trim()
    }

    // ---------------------------------------------------------------- tokens

    private sealed interface Tok
    private data class TimeTok(val minutes: Int) : Tok
    private data class NumTok(val value: Int) : Tok
    private data class WordTok(val word: String) : Tok
    private data object Dash : Tok
    private data object Comma : Tok
    private data object Colon : Tok
    private data object Plus : Tok
    private data object Always : Tok // 24/7

    private val TIME_RE = Regex("""(\d{1,2})[:.h](\d{2})""")

    private fun tokenize(s: String): List<Tok> {
        val out = mutableListOf<Tok>()
        var i = 0
        while (i < s.length) {
            val c = s[i]
            when {
                c.isWhitespace() -> i++
                s.startsWith("24/7", i) -> { out += Always; i += 4 }
                c == '"' -> { // comment: skip to closing quote
                    val close = s.indexOf('"', i + 1)
                    i = if (close < 0) s.length else close + 1
                }
                c == '(' || c == ')' || c == '[' || c == ']' -> {
                    // Things like "Mo[1]" or "(sunset-01:00)": skip the bracketed part.
                    if (c == '[' || c == '(') {
                        val close = s.indexOf(if (c == '[') ']' else ')', i + 1)
                        i = if (close < 0) s.length else close + 1
                    } else i++
                }
                c.isDigit() -> {
                    val m = TIME_RE.matchAt(s, i)
                    if (m != null) {
                        out += TimeTok(m.groupValues[1].toInt() * 60 + m.groupValues[2].toInt())
                        i += m.value.length
                    } else {
                        var j = i
                        while (j < s.length && s[j].isDigit()) j++
                        out += NumTok(s.substring(i, j).take(6).toInt())
                        i = j
                    }
                }
                c.isLetter() -> {
                    var j = i
                    while (j < s.length && s[j].isLetter()) j++
                    out += WordTok(s.substring(i, j).lowercase())
                    i = j
                }
                c == '-' -> { out += Dash; i++ }
                c == ',' -> { out += Comma; i++ }
                c == ':' -> { out += Colon; i++ }
                c == '+' -> { out += Plus; i++ }
                else -> i++
            }
        }
        return out
    }

    private fun weekday(word: String): Int? = when (word) {
        "mo", "mon", "monday", "isnin" -> 0
        "tu", "tue", "tues", "tuesday", "selasa" -> 1
        "we", "wed", "wednesday", "rabu" -> 2
        "th", "thu", "thur", "thurs", "thursday", "khamis" -> 3
        "fr", "fri", "friday", "jumaat" -> 4
        "sa", "sat", "saturday", "sabtu" -> 5
        "su", "sun", "sunday", "ahad" -> 6
        else -> null
    }

    private val ALL_DAYS_WORDS = setOf("daily", "everyday", "every", "day", "days", "setiap", "hari", "week", "weekdays", "weekends")
    private val HOLIDAY_WORDS = setOf("ph", "sh")
    private val UNSUPPORTED_WORDS = setOf(
        "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec",
        "easter", "wk", "unknown", "ramadan",
    )
    private val OFF_WORDS = setOf("off", "closed", "close", "tutup")
    private val OPEN_END_WORDS = setOf("late", "closing", "last")

    private class Rule(val days: Set<Int>, val spans: List<Interval>, val off: Boolean, val additional: Boolean)

    private fun isSelectorStart(t: Tok?): Boolean =
        t is WordTok && (weekday(t.word) != null || t.word in HOLIDAY_WORDS || t.word in ALL_DAYS_WORDS)

    /** Parses one `;`-separated rule, which may carry extra `,`-separated rules. Null if nothing usable. */
    private fun parseRuleText(tokens: List<Tok>): List<Rule>? {
        val rules = mutableListOf<Rule>()
        var i = 0
        var additional = false
        while (i < tokens.size) {
            // ---- weekday selector
            val days = sortedSetOf<Int>()
            var sawDay = false
            var sawHoliday = false
            var unsupported = false
            var sawAlways = false
            selector@ while (i < tokens.size) {
                val t = tokens[i]
                when {
                    t is WordTok && weekday(t.word) != null -> {
                        val from = weekday(t.word)!!
                        val maybeTo = (tokens.getOrNull(i + 2) as? WordTok)?.let { weekday(it.word) }
                        if (tokens.getOrNull(i + 1) == Dash && maybeTo != null) {
                            var d = from
                            while (true) {
                                days += d
                                if (d == maybeTo) break
                                d = (d + 1) % 7
                            }
                            i += 3
                        } else {
                            days += from
                            i++
                        }
                        sawDay = true
                    }
                    t is WordTok && t.word in HOLIDAY_WORDS -> { sawHoliday = true; i++ }
                    t is WordTok && t.word == "weekdays" -> { days += 0..4; sawDay = true; i++ }
                    t is WordTok && t.word == "weekends" -> { days += 5..6; sawDay = true; i++ }
                    t is WordTok && t.word in ALL_DAYS_WORDS -> { days += 0..6; sawDay = true; i++ }
                    t is WordTok && t.word in UNSUPPORTED_WORDS -> { unsupported = true; i++ }
                    t == Comma && isSelectorStart(tokens.getOrNull(i + 1)) -> i++
                    t is NumTok && (unsupported || tokens.getOrNull(i + 1) is WordTok) -> { unsupported = true; i++ }
                    t == Colon -> { i++; break@selector }
                    else -> break@selector
                }
            }

            // ---- times and modifiers
            val spans = mutableListOf<Interval>()
            var off = false
            var open = false
            var junk = false
            var startsNext = false
            times@ while (i < tokens.size) {
                val t = tokens[i]
                val start = timeOf(t)
                when {
                    start != null && tokens.getOrNull(i + 1) == Dash && timeOf(tokens.getOrNull(i + 2)) != null -> {
                        spans += span(start, timeOf(tokens[i + 2])!!, bareEnd = tokens[i + 2] is NumTok)
                        i += 3
                        if (tokens.getOrNull(i) == Plus) i++
                    }
                    start != null && tokens.getOrNull(i + 1) == Plus -> {
                        spans += openEnded(start); i += 2
                    }
                    start != null && tokens.getOrNull(i + 1) == Dash &&
                        (tokens.getOrNull(i + 2) as? WordTok)?.word in OPEN_END_WORDS -> {
                        spans += openEnded(start); i += 3
                    }
                    t == Always -> { sawAlways = true; i++ }
                    t is WordTok && t.word in OFF_WORDS -> { off = true; i++ }
                    t is WordTok && t.word == "open" -> { open = true; i++ }
                    t is WordTok && t.word in UNSUPPORTED_WORDS -> { unsupported = true; i++ }
                    t == Comma && isSelectorStart(tokens.getOrNull(i + 1)) -> { i++; startsNext = true; break@times }
                    t == Comma -> i++
                    isSelectorStart(t) && (spans.isNotEmpty() || off || sawAlways) -> { startsNext = true; break@times }
                    else -> { junk = true; i++ }
                }
            }

            val selectedDays: Set<Int> = if (sawDay) days else (0..6).toSet()
            val usable = when {
                unsupported -> false
                sawHoliday && !sawDay -> false
                sawAlways -> true
                off -> true
                spans.isNotEmpty() -> true
                open -> true
                sawDay && !junk -> true // "Mo-Fr" alone means open all day
                else -> false
            }
            if (usable) {
                val finalSpans = when {
                    off -> emptyList()
                    sawAlways || spans.isEmpty() -> listOf(Interval(0, MINUTES_PER_DAY))
                    else -> spans
                }
                rules += Rule(selectedDays, finalSpans, off, additional)
            }
            additional = true
            if (!startsNext) break
        }
        return rules.ifEmpty { null }
    }

    private fun timeOf(t: Tok?): Int? = when (t) {
        is TimeTok -> t.minutes.takeIf { it <= 48 * 60 }
        is NumTok -> (t.value * 60).takeIf { t.value in 0..48 }
        else -> null
    }

    private fun span(start: Int, endRaw: Int, bareEnd: Boolean = false): Interval {
        var end = endRaw
        // A bare "9-5" means 9 AM to 5 PM, not an overnight shift.
        if (bareEnd && end <= start && end + 12 * 60 > start) end += 12 * 60
        if (end <= start) end += MINUTES_PER_DAY
        if (end - start > MINUTES_PER_DAY) end = start + MINUTES_PER_DAY
        return Interval(start, end)
    }

    /** "22:00+" has no stated closing time; count it until midnight only, so we never over-promise. */
    private fun openEnded(start: Int) = Interval(start, maxOf(start + 60, MINUTES_PER_DAY))
}
