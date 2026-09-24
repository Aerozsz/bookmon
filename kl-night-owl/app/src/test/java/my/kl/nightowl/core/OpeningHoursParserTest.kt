package my.kl.nightowl.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class OpeningHoursParserTest {

    private fun parse(s: String) = assertNotNull("could not parse '$s'", OpeningHoursParser.parse(s)).let { OpeningHoursParser.parse(s)!! }
    private fun at(day: Int, h: Int, m: Int = 0) = day * MINUTES_PER_DAY + h * 60 + m
    private val text = HoursText(use24h = false)

    @Test fun twentyFourSeven() {
        val s = parse("24/7")
        assertTrue(s.isAlwaysOpen)
        assertTrue(s.isOpenAtNight)
        assertNull(s.nextChange(at(2, 3)))
    }

    @Test fun twentyFourSevenVariants() {
        for (raw in listOf("Mo-Su 00:00-24:00", "24 hours", "Open 24 Hours", "24hrs", "Daily 24 hours", "00:00-24:00", "24/7; PH off")) {
            assertTrue(raw, parse(raw).isAlwaysOpen)
        }
    }

    @Test fun overnightRunsIntoNextDay() {
        val s = parse("Mo-Su 18:00-03:00")
        assertTrue(s.isOpenAt(at(0, 23)))
        assertTrue(s.isOpenAt(at(1, 2, 59)))
        assertFalse(s.isOpenAt(at(1, 3)))
        assertEquals(listOf(Interval(0, 180)), s.nightCoverage(1))
        assertEquals("Open until 3:00 AM", text.night(s.nightCoverage(1)))
        // Sunday night spills into Monday morning.
        assertTrue(s.isOpenAt(at(0, 1)))
    }

    @Test fun closesAtMidnightIsNotANightPlace() {
        assertFalse(parse("Mo-Su 10:00-24:00").isOpenAtNight)
        assertFalse(parse("Mo-Su 10:00-00:00").isOpenAtNight)
        assertFalse(parse("Mo-Fr 08:00-17:00; Sa 09:00-13:00; Su,PH off").isOpenAtNight)
    }

    @Test fun laterRuleOverridesEarlierForSameDays() {
        val s = parse("Mo-Su 08:00-22:00; Fr-Sa 08:00-02:00")
        assertFalse(s.isOpenAt(at(3, 23))) // Thursday 23:00
        assertTrue(s.isOpenAt(at(4, 23))) // Friday 23:00
        assertTrue(s.isOpenAt(at(5, 1))) // Saturday 01:00 (Friday night)
        assertTrue(s.isOpenAt(at(6, 1))) // Sunday 01:00 (Saturday night)
        assertFalse(s.isOpenAt(at(0, 1))) // Monday 01:00
    }

    @Test fun commaSeparatedRulesAndTimeLists() {
        val s = parse("Mo-Th 11:00-15:00,18:00-02:00, Fr-Sa 11:00-04:00")
        assertFalse(s.isOpenAt(at(0, 16)))
        assertTrue(s.isOpenAt(at(1, 1)))
        assertTrue(s.isOpenAt(at(5, 3, 30)))
        assertFalse(s.isOpenAt(at(0, 1))) // Sunday night: no rule for Sunday
    }

    @Test fun wrappingWeekdayRange() {
        val s = parse("Su-Th 17:00-01:00")
        assertTrue(s.isOpenAt(at(0, 0, 30))) // Sunday night -> Monday 00:30
        assertFalse(s.isOpenAt(at(5, 0, 30))) // Friday night -> Saturday: closed
    }

    @Test fun amPmAndFreeText() {
        val s = parse("Mon-Sun: 7am - 2am")
        assertTrue(s.isOpenAt(at(2, 1, 59)))
        assertFalse(s.isOpenAt(at(2, 2, 0)))
        assertTrue(parse("Daily 6pm to 5am").isOpenAt(at(3, 4)))
    }

    @Test fun bareHoursAreDaytime() {
        val s = parse("Mo-Fr 9-5")
        assertFalse(s.isOpenAtNight)
        assertTrue(s.isOpenAt(at(0, 16)))
    }

    @Test fun earlyOpener() {
        val s = parse("Mo-Su 05:00-14:00")
        assertTrue(s.isOpenAtNight)
        assertEquals("Opens at 5:00 AM", text.night(s.nightCoverage(2)))
    }

    @Test fun offAndHolidays() {
        val s = parse("Mo-Sa 07:00-03:00; Su off; PH off")
        assertTrue(s.isOpenAt(at(6, 2))) // Saturday night into Sunday 2 AM
        assertFalse(s.isOpenAt(at(6, 12)))
        assertFalse(s.isOpenAt(at(0, 2))) // Sunday closed so nothing into Monday
    }

    @Test fun nextChangeCrossesTheWeek() {
        val s = parse("Mo-Su 18:00-03:00")
        assertEquals(at(0, 3), s.nextChange(at(0, 1)))
        assertEquals(at(0, 18), s.nextChange(at(0, 12)))
        // Sunday 23:00 -> closes Monday 03:00 of the following week.
        assertEquals(MINUTES_PER_WEEK + 180, s.nextChange(at(6, 23)))
    }

    @Test fun dateRulesAreIgnored() {
        val s = parse("Mo-Su 10:00-02:00; Dec 25 off")
        assertTrue(s.isOpenAt(at(3, 1)))
    }

    @Test fun garbageIsNull() {
        assertNull(OpeningHoursParser.parse("call us"))
        assertNull(OpeningHoursParser.parse(""))
        assertNull(OpeningHoursParser.parse("PH off"))
    }

    @Test fun dayText() {
        val s = parse("Mo-Fr 07:00-03:00; Sa 00:00-24:00; Su off")
        assertEquals("7:00 AM – 3:00 AM", text.day(s.days[0]))
        assertEquals("Open 24 hours", text.day(s.days[5]))
        assertEquals("Closed", text.day(s.days[6]))
        assertEquals("07:00 – 03:00", HoursText(use24h = true).day(s.days[0]))
    }

    @Test fun realWorldFormatsFromKualaLumpur() {
        // Narrow no-break spaces, as copied from Google Maps.
        val a = parse("Monday, 5\u202Fpm\u20133\u202Fam")
        assertTrue(a.isOpenAt(at(1, 2)))
        assertFalse(a.isOpenAt(at(2, 2)))
        assertTrue(parse("Tuesday-Sunday 830pm-5am").isOpenAt(at(3, 4)))
        assertFalse(parse("Mo-Su 0900-1800").isOpenAtNight)
        assertFalse(parse("Mo-Su 00:00-23:59").isOpenAt(at(2, 23, 59)).not())
        assertTrue(parse("Mo-Su,PH 00:00-23:59").isAlwaysOpen)
    }

    @Test fun twelveHourSlips() {
        assertFalse("10:00-10:00 is 10 AM-10 PM", parse("Mo-Su 10:00-10:00").isOpenAtNight)
        assertFalse("17:00-12:00 is until midnight", parse("Mo-Sa 18:00-12:00").isOpenAtNight)
        assertFalse("10:00-09:00 is 10 AM-9 PM", parse("Tu-Su 10:00-09:00").isOpenAtNight)
        // Real long hours stay as they are.
        assertTrue(parse("Mo-Su 07:30-05:00").isOpenAt(at(2, 4)))
        assertTrue(parse("Mo-Su 06:00-05:00").isOpenAt(at(2, 4)))
        assertTrue(parse("00:00-00:00").isAlwaysOpen)
    }

    @Test fun offWithTimesOnlyClosesThoseTimes() {
        val s = parse("24/7; Fr 13:00-14:00 off")
        assertTrue(s.isOpenAt(at(4, 2)))
        assertFalse(s.isOpenAt(at(4, 13, 30)))
        assertTrue(s.isOpenAt(at(4, 23)))
    }

    @Test fun daysWithoutTimesAreUnknown() {
        assertNull(OpeningHoursParser.parse("Mon - Saturday"))
        assertNull(OpeningHoursParser.parse("Sa,Su"))
    }

    @Test fun malformedMinutesDontInventNightHours() {
        val s = OpeningHoursParser.parse("Mo-Su 06:030-22:30")
        assertTrue(s == null || !s.isOpenAtNight)
    }

    @Test fun allNightText() {
        val s = parse("Mo-Su 20:00-08:00")
        assertEquals("Open all night", text.night(s.nightCoverage(4)))
    }
}
