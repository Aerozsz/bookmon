package my.kl.nightowl.core

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Turns an Overpass API response (OpenStreetMap data) into the list of places that are open
 * at some point between midnight and 6 AM.
 */
object OsmParser {

    class Result(
        val places: List<Place>,
        val elementCount: Int,
        /** When the OpenStreetMap snapshot was taken, ISO-8601, if the response says. */
        val snapshot: String?,
        /** opening_hours values we could not read; used for the build-time data report. */
        val unreadableHours: List<String>,
        /** Server message, e.g. "runtime error: Query timed out". */
        val remark: String?,
    )

    fun parse(text: String): Result {
        val root = Json.parseToJsonElement(text).jsonObject
        val snapshot = root["osm3s"]?.jsonObject?.get("timestamp_osm_base")?.jsonPrimitive?.contentOrNull
        val elements = root["elements"]?.jsonArray.orEmpty()
        val places = mutableListOf<Place>()
        val unreadable = mutableListOf<String>()
        for (element in elements) {
            val obj = element as? JsonObject ?: continue
            val tags = (obj["tags"] as? JsonObject)
                ?.mapValues { it.value.jsonPrimitive.contentOrNull.orEmpty() }
                ?: continue
            val center = obj["center"] as? JsonObject
            val lat = (obj["lat"] ?: center?.get("lat"))?.jsonPrimitive?.doubleOrNull ?: continue
            val lon = (obj["lon"] ?: center?.get("lon"))?.jsonPrimitive?.doubleOrNull ?: continue
            val id = "${obj["type"]?.jsonPrimitive?.contentOrNull ?: "node"}/${obj["id"]?.jsonPrimitive?.contentOrNull}"
            when (val outcome = PlaceBuilder.build(id, tags, lat, lon)) {
                is PlaceBuilder.Outcome.Night -> places += outcome.place
                is PlaceBuilder.Outcome.UnreadableHours -> unreadable += outcome.raw
                PlaceBuilder.Outcome.Skip -> Unit
            }
        }
        val remark = root["remark"]?.jsonPrimitive?.contentOrNull
        return Result(dedupe(places), elements.size, snapshot, unreadable, remark)
    }

    /** OSM sometimes has the same shop twice (a point and a building outline). Keep the richer one. */
    private fun dedupe(places: List<Place>): List<Place> {
        val kept = mutableListOf<Place>()
        for ((_, group) in places.groupBy { it.name.lowercase().filter(Char::isLetterOrDigit) }) {
            val ranked = group.sortedWith(
                compareByDescending<Place> { it.hoursVerified }
                    .thenByDescending { it.address != null }
                    .thenBy { it.id },
            )
            val chosen = mutableListOf<Place>()
            for (p in ranked) {
                if (chosen.none { Geo.distanceMeters(it.lat, it.lon, p.lat, p.lon) < 60.0 }) chosen += p
            }
            kept += chosen
        }
        return kept.sortedBy { it.name.lowercase() }
    }
}

/** Decides whether an OSM feature is a night place, and describes it. */
object PlaceBuilder {

    sealed interface Outcome {
        class Night(val place: Place) : Outcome
        class UnreadableHours(val raw: String) : Outcome
        data object Skip : Outcome
    }

    fun build(id: String, tags: Map<String, String>, lat: Double, lon: Double): Outcome {
        if (tags.keys.any { it.startsWith("disused:") || it.startsWith("abandoned:") || it.startsWith("was:") }) return Outcome.Skip
        if (tags["access"] in setOf("private", "no", "customers")) return Outcome.Skip
        val category = categorize(tags) ?: return Outcome.Skip

        val rawHours = tags["opening_hours"]?.trim()?.takeIf { it.isNotEmpty() }
        val parsed = OpeningHoursParser.parse(rawHours)
        val schedule: WeeklySchedule
        val verified: Boolean
        when {
            parsed != null -> {
                schedule = parsed
                verified = true
            }
            usuallyOpen24h(category, tags) -> {
                schedule = WeeklySchedule.alwaysOpen()
                verified = false
            }
            rawHours != null -> return Outcome.UnreadableHours(rawHours)
            else -> return Outcome.Skip
        }
        if (!schedule.isOpenAtNight) return Outcome.Skip

        val kind = describe(category, tags)
        val name = displayName(tags) ?: kind.substringBefore(" ·")
        return Outcome.Night(
            Place(
                id = id,
                name = name,
                category = category,
                kind = kind,
                lat = lat,
                lon = lon,
                address = address(tags),
                openingHoursRaw = rawHours,
                schedule = schedule,
                hoursVerified = verified,
                phone = tags["phone"] ?: tags["contact:phone"],
                website = tags["website"] ?: tags["contact:website"],
            ),
        )
    }

    fun categorize(tags: Map<String, String>): Category? {
        when (tags["amenity"]) {
            "restaurant", "food_court" -> return Category.RESTAURANT
            "fast_food" -> return Category.FAST_FOOD
            "cafe", "ice_cream" -> return Category.CAFE
            "bar", "pub", "nightclub", "biergarten" -> return Category.NIGHTLIFE
            "fuel" -> return Category.PETROL
            "pharmacy" -> return Category.PHARMACY
            "internet_cafe" -> return Category.OTHER
        }
        if (tags["shop"] == "convenience" && PETROL_SHOP.containsMatchIn(tags["name"].orEmpty() + " " + tags["brand"].orEmpty())) {
            return Category.PETROL
        }
        return when (tags["shop"]) {
            null, "no", "vacant" -> null
            "yes" -> Category.OTHER
            "convenience", "kiosk", "newsagent" -> Category.KONBINI
            "supermarket", "grocery", "greengrocer", "general", "bakery", "butcher", "seafood",
            "deli", "frozen_food", "dairy", "health_food", "pastry", "confectionery", "beverages" -> Category.GROCERY
            "chemist", "medical_supply" -> Category.PHARMACY
            else -> Category.OTHER
        }
    }

    /** Shops inside petrol stations, which OSM often tags as plain convenience stores. */
    private val PETROL_SHOP = Regex("""(?i)\b(mesra|shell select|bhpetromart|petromart|petron treats|caltex star ?mart)\b""")

    // Chains whose Kuala Lumpur branches are open round the clock, used only when OSM has no hours.
    // Names must start with the chain name so that e.g. "Malliga Family Mart" doesn't count.
    private val CONVENIENCE_24H = Regex("""(?i)^\s*(7[\s-]?eleven|seven[\s-]?eleven|family\s?mart|kk\s?(super\s?)?mart|lawson|cu)\b""")
    private val FUEL_24H = Regex("""(?i)^\s*(petronas|shell|petron|caltex|bhp|bhpetrol)\b""")
    private val MAMAK_24H = Regex("""(?i)^\s*(restoran\s+)?(nasi\s+kandar\s+pelita|pelita\s+nasi\s+kandar|pelita|(nasi\s+kandar\s+)?ali\s+maju)\b""")

    private fun usuallyOpen24h(category: Category, tags: Map<String, String>): Boolean {
        val candidates = listOfNotNull(tags["brand"], tags["name"], tags["name:en"])
        val chain = when (category) {
            Category.KONBINI -> CONVENIENCE_24H
            Category.PETROL -> FUEL_24H
            Category.RESTAURANT, Category.FAST_FOOD -> MAMAK_24H
            else -> return false
        }
        return candidates.any { chain.containsMatchIn(it) }
    }

    private fun displayName(tags: Map<String, String>): String? {
        val name = tags["name"]?.trim()?.takeIf { it.isNotEmpty() }
        val en = tags["name:en"]?.trim()?.takeIf { it.isNotEmpty() }
        val brand = tags["brand"]?.trim()?.takeIf { it.isNotEmpty() }
        return when {
            name != null && name.any { it in 'A'..'Z' || it in 'a'..'z' } -> name
            en != null -> en
            name != null -> name
            else -> brand
        }
    }

    private fun address(tags: Map<String, String>): String? {
        tags["addr:full"]?.let { return it }
        val street = listOfNotNull(tags["addr:housenumber"], tags["addr:street"]).joinToString(", ")
        val parts = listOfNotNull(
            street.ifEmpty { null },
            tags["addr:suburb"] ?: tags["addr:neighbourhood"],
            tags["addr:postcode"]?.let { pc -> tags["addr:city"]?.let { "$pc $it" } ?: pc } ?: tags["addr:city"],
        )
        return parts.joinToString(", ").ifEmpty { null }
    }

    /** Plain-words description: "Restaurant · Indian, Malaysian", "Convenience store · 7-Eleven". */
    fun describe(category: Category, tags: Map<String, String>): String {
        val amenity = tags["amenity"]
        val shop = tags["shop"]
        val cuisine = tags["cuisine"]?.split(';', ',')
            ?.map { it.trim().replace('_', ' ') }
            ?.filter { it.isNotEmpty() && it != "yes" }
            ?.take(3)
            ?.joinToString(", ") { it.replaceFirstChar(Char::uppercase) }
            ?.ifEmpty { null }
        val brand = tags["brand"]?.takeIf { b ->
            !tags["name"].orEmpty().contains(b, ignoreCase = true)
        }
        val nameLower = tags["name"].orEmpty().lowercase()
        val base = when {
            amenity == "restaurant" && (
                "nasi kandar" in nameLower || "mamak" in nameLower ||
                    tags["cuisine"].orEmpty().contains("mamak") || tags["cuisine"].orEmpty().contains("indian_muslim")
                ) -> "Mamak restaurant"
            amenity == "restaurant" -> "Restaurant"
            amenity == "food_court" -> "Food court / hawker stalls"
            amenity == "fast_food" -> "Fast food"
            amenity == "cafe" && ("kopitiam" in nameLower || tags["cuisine"] == "coffee_shop") -> "Kopitiam / café"
            amenity == "cafe" -> "Café"
            amenity == "ice_cream" -> "Ice cream & desserts"
            amenity == "bar" -> "Bar"
            amenity == "pub" -> "Pub"
            amenity == "nightclub" -> "Nightclub"
            amenity == "biergarten" -> "Beer garden"
            amenity == "fuel" -> if (shop != null) "Petrol station with shop" else "Petrol station"
            amenity == "pharmacy" -> "Pharmacy"
            amenity == "internet_cafe" -> "Cybercafé"
            category == Category.PETROL -> "Petrol station shop"
            shop == "convenience" -> "Convenience store"
            shop == "kiosk" -> "Kiosk"
            shop == "newsagent" -> "Newsagent & convenience"
            shop == "supermarket" -> "Supermarket"
            shop == "general" -> "Sundry shop (kedai runcit)"
            shop == "chemist" -> "Pharmacy & personal care"
            shop != null && shop != "yes" -> shop.replace('_', ' ').replaceFirstChar(Char::uppercase) + " shop"
            else -> category.label
        }
        val detail = when (category) {
            Category.RESTAURANT, Category.FAST_FOOD, Category.CAFE -> cuisine ?: brand
            else -> brand
        }
        return if (detail != null) "$base · $detail" else base
    }
}

/** Builds the Overpass query from the shared template in `assets/overpass_query.txt`. */
object OverpassQuery {
    const val KL_AREA = """area["ISO3166-2"="MY-14"]->.kl;"""
    const val KL_BBOX = "(3.03,101.60,3.26,101.77)"

    val ENDPOINTS = listOf(
        "https://overpass-api.de/api/interpreter",
        "https://overpass.private.coffee/api/interpreter",
        "https://overpass.kumi.systems/api/interpreter",
        "https://maps.mail.ru/osm/tools/overpass/api/interpreter",
    )

    fun build(template: String, useArea: Boolean): String =
        template
            .replace("{{AREA}}", if (useArea) KL_AREA else "")
            .replace("{{SCOPE}}", if (useArea) "(area.kl)" else KL_BBOX)
}
