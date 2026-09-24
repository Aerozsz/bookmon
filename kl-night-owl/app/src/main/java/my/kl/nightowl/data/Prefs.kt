package my.kl.nightowl.data

import android.content.Context

class Prefs(context: Context) {
    private val sp = context.getSharedPreferences("night_owl", Context.MODE_PRIVATE)

    var travelMode: TravelMode
        get() = TravelMode.entries.firstOrNull { it.name == sp.getString("travel_mode", null) } ?: TravelMode.DRIVE
        set(value) = sp.edit().putString("travel_mode", value.name).apply()

    var darkMap: Boolean
        get() = sp.getBoolean("dark_map", true)
        set(value) = sp.edit().putBoolean("dark_map", value).apply()

    var askedForLocation: Boolean
        get() = sp.getBoolean("asked_location", false)
        set(value) = sp.edit().putBoolean("asked_location", value).apply()
}
