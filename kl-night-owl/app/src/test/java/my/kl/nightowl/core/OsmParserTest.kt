package my.kl.nightowl.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class OsmParserTest {

    private val sample = """
        {
          "version": 0.6,
          "osm3s": { "timestamp_osm_base": "2026-09-20T10:00:00Z" },
          "elements": [
            { "type": "node", "id": 1, "lat": 3.1466, "lon": 101.7101,
              "tags": { "shop": "convenience", "brand": "7-Eleven", "name": "7-Eleven", "opening_hours": "24/7" } },
            { "type": "node", "id": 2, "lat": 3.1467, "lon": 101.7102,
              "tags": { "shop": "convenience", "name": "7-Eleven" } },
            { "type": "node", "id": 3, "lat": 3.1500, "lon": 101.7000,
              "tags": { "shop": "convenience", "name": "FamilyMart Bukit Bintang" } },
            { "type": "way", "id": 4, "center": { "lat": 3.1580, "lon": 101.7120 },
              "tags": { "amenity": "restaurant", "name": "Restoran Nasi Kandar Contoh", "cuisine": "indian;malaysian",
                        "opening_hours": "Mo-Su 07:00-03:00", "addr:street": "Jalan Ampang", "addr:housenumber": "12" } },
            { "type": "node", "id": 5, "lat": 3.14, "lon": 101.69,
              "tags": { "amenity": "cafe", "name": "Day Cafe", "opening_hours": "Mo-Fr 08:00-18:00" } },
            { "type": "node", "id": 6, "lat": 3.14, "lon": 101.69,
              "tags": { "amenity": "bar", "name": "Weird Hours Bar", "opening_hours": "call us" } },
            { "type": "node", "id": 7, "lat": 3.13, "lon": 101.68,
              "tags": { "amenity": "fuel", "brand": "Petronas", "name": "Petronas Jalan Tun Razak" } },
            { "type": "node", "id": 8, "lat": 3.12, "lon": 101.67,
              "tags": { "shop": "convenience", "name": "Kedai Runcit Ah Chong" } },
            { "type": "node", "id": 9, "lat": 3.11, "lon": 101.66,
              "tags": { "disused:shop": "convenience", "name": "Old 7-Eleven", "opening_hours": "24/7" } }
          ]
        }
    """.trimIndent()

    @Test fun parsesNightPlaces() {
        val result = OsmParser.parse(sample)
        assertEquals(9, result.elementCount)
        assertEquals("2026-09-20T10:00:00Z", result.snapshot)
        val byName = result.places.associateBy { it.name }

        // Duplicate 7-Eleven 15 m apart collapses to the one with published hours.
        assertEquals(1, result.places.count { it.name == "7-Eleven" })
        assertTrue(byName.getValue("7-Eleven").hoursVerified)

        val fm = byName.getValue("FamilyMart Bukit Bintang")
        assertEquals(Category.KONBINI, fm.category)
        assertFalse(fm.hoursVerified)

        val mamak = byName.getValue("Restoran Nasi Kandar Contoh")
        assertEquals(Category.RESTAURANT, mamak.category)
        assertEquals("Mamak restaurant · Indian, Malaysian", mamak.kind)
        assertEquals("12, Jalan Ampang", mamak.address)

        assertEquals(Category.PETROL, byName.getValue("Petronas Jalan Tun Razak").category)

        assertFalse("day-only cafe", "Day Cafe" in byName)
        assertFalse("no hours, not a 24h chain", "Kedai Runcit Ah Chong" in byName)
        assertFalse("disused", "Old 7-Eleven" in byName)
        assertEquals(listOf("call us"), result.unreadableHours)
    }

    @Test fun describesKinds() {
        assertEquals("Convenience store", PlaceBuilder.describe(Category.KONBINI, mapOf("shop" to "convenience", "name" to "7-Eleven", "brand" to "7-Eleven")))
        assertEquals("Mobile phone shop", PlaceBuilder.describe(Category.OTHER, mapOf("shop" to "mobile_phone")))
        assertEquals("Fast food · Burger", PlaceBuilder.describe(Category.FAST_FOOD, mapOf("amenity" to "fast_food", "cuisine" to "burger")))
    }

    @Test fun queryTemplate() {
        val t = "{{AREA}}(nwr[\"shop\"]{{SCOPE}};);"
        assertEquals("""area["ISO3166-2"="MY-14"]->.kl;(nwr["shop"](area.kl);)""" + ";", OverpassQuery.build(t, useArea = true))
        assertEquals("""(nwr["shop"](3.03,101.60,3.26,101.77);)""" + ";", OverpassQuery.build(t, useArea = false))
    }
}
