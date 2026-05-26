package com.worldclock.app.data

import android.content.Context

class CityRepository(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getSavedCities(): List<City> {
        val raw = prefs.getString(KEY_CITIES, DEFAULT_IDS) ?: DEFAULT_IDS
        return raw.split(",")
            .filter { it.isNotBlank() }
            .mapNotNull { id -> ALL_CITIES.find { it.id == id } }
    }

    fun addCity(cityId: String) {
        val current = getSavedCities().map { it.id }.toMutableList()
        if (!current.contains(cityId)) {
            current.add(cityId)
            prefs.edit().putString(KEY_CITIES, current.joinToString(",")).apply()
        }
    }

    fun removeCity(cityId: String) {
        val updated = getSavedCities().map { it.id }.filter { it != cityId }
        prefs.edit().putString(KEY_CITIES, updated.joinToString(",")).apply()
    }

    fun reorderCities(ids: List<String>) {
        prefs.edit().putString(KEY_CITIES, ids.joinToString(",")).apply()
    }

    companion object {
        private const val PREFS_NAME = "world_clock_prefs"
        private const val KEY_CITIES = "saved_city_ids"
        private const val DEFAULT_IDS = "new_york,london,tokyo,sydney,dubai"

        val ALL_CITIES = listOf(
            City("new_york",      "New York",       "United States",  "America/New_York",                  "🇺🇸"),
            City("los_angeles",   "Los Angeles",    "United States",  "America/Los_Angeles",               "🇺🇸"),
            City("chicago",       "Chicago",        "United States",  "America/Chicago",                   "🇺🇸"),
            City("miami",         "Miami",          "United States",  "America/New_York",                  "🇺🇸"),
            City("seattle",       "Seattle",        "United States",  "America/Los_Angeles",               "🇺🇸"),
            City("honolulu",      "Honolulu",       "United States",  "Pacific/Honolulu",                  "🇺🇸"),
            City("anchorage",     "Anchorage",      "United States",  "America/Anchorage",                 "🇺🇸"),
            City("toronto",       "Toronto",        "Canada",         "America/Toronto",                   "🇨🇦"),
            City("vancouver",     "Vancouver",      "Canada",         "America/Vancouver",                 "🇨🇦"),
            City("mexico_city",   "Mexico City",    "Mexico",         "America/Mexico_City",               "🇲🇽"),
            City("sao_paulo",     "São Paulo",      "Brazil",         "America/Sao_Paulo",                 "🇧🇷"),
            City("buenos_aires",  "Buenos Aires",   "Argentina",      "America/Argentina/Buenos_Aires",    "🇦🇷"),
            City("bogota",        "Bogotá",         "Colombia",       "America/Bogota",                    "🇨🇴"),
            City("lima",          "Lima",           "Peru",           "America/Lima",                      "🇵🇪"),
            City("london",        "London",         "United Kingdom", "Europe/London",                     "🇬🇧"),
            City("paris",         "Paris",          "France",         "Europe/Paris",                      "🇫🇷"),
            City("berlin",        "Berlin",         "Germany",        "Europe/Berlin",                     "🇩🇪"),
            City("madrid",        "Madrid",         "Spain",          "Europe/Madrid",                     "🇪🇸"),
            City("rome",          "Rome",           "Italy",          "Europe/Rome",                       "🇮🇹"),
            City("amsterdam",     "Amsterdam",      "Netherlands",    "Europe/Amsterdam",                  "🇳🇱"),
            City("stockholm",     "Stockholm",      "Sweden",         "Europe/Stockholm",                  "🇸🇪"),
            City("zurich",        "Zürich",         "Switzerland",    "Europe/Zurich",                     "🇨🇭"),
            City("moscow",        "Moscow",         "Russia",         "Europe/Moscow",                     "🇷🇺"),
            City("istanbul",      "Istanbul",       "Turkey",         "Europe/Istanbul",                   "🇹🇷"),
            City("cairo",         "Cairo",          "Egypt",          "Africa/Cairo",                      "🇪🇬"),
            City("lagos",         "Lagos",          "Nigeria",        "Africa/Lagos",                      "🇳🇬"),
            City("nairobi",       "Nairobi",        "Kenya",          "Africa/Nairobi",                    "🇰🇪"),
            City("johannesburg",  "Johannesburg",   "South Africa",   "Africa/Johannesburg",               "🇿🇦"),
            City("dubai",         "Dubai",          "UAE",            "Asia/Dubai",                        "🇦🇪"),
            City("riyadh",        "Riyadh",         "Saudi Arabia",   "Asia/Riyadh",                       "🇸🇦"),
            City("karachi",       "Karachi",        "Pakistan",       "Asia/Karachi",                      "🇵🇰"),
            City("mumbai",        "Mumbai",         "India",          "Asia/Kolkata",                      "🇮🇳"),
            City("new_delhi",     "New Delhi",      "India",          "Asia/Kolkata",                      "🇮🇳"),
            City("dhaka",         "Dhaka",          "Bangladesh",     "Asia/Dhaka",                        "🇧🇩"),
            City("bangkok",       "Bangkok",        "Thailand",       "Asia/Bangkok",                      "🇹🇭"),
            City("singapore",     "Singapore",      "Singapore",      "Asia/Singapore",                    "🇸🇬"),
            City("jakarta",       "Jakarta",        "Indonesia",      "Asia/Jakarta",                      "🇮🇩"),
            City("hong_kong",     "Hong Kong",      "China",          "Asia/Hong_Kong",                    "🇭🇰"),
            City("beijing",       "Beijing",        "China",          "Asia/Shanghai",                     "🇨🇳"),
            City("shanghai",      "Shanghai",       "China",          "Asia/Shanghai",                     "🇨🇳"),
            City("seoul",         "Seoul",          "South Korea",    "Asia/Seoul",                        "🇰🇷"),
            City("tokyo",         "Tokyo",          "Japan",          "Asia/Tokyo",                        "🇯🇵"),
            City("sydney",        "Sydney",         "Australia",      "Australia/Sydney",                  "🇦🇺"),
            City("melbourne",     "Melbourne",      "Australia",      "Australia/Melbourne",               "🇦🇺"),
            City("auckland",      "Auckland",       "New Zealand",    "Pacific/Auckland",                  "🇳🇿"),
        )
    }
}
