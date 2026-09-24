package my.kl.nightowl.core

import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

data class Place(
    /** OpenStreetMap id, e.g. "node/123456". */
    val id: String,
    val name: String,
    val category: Category,
    /** What the place is, in plain words: "Convenience store · 7-Eleven". */
    val kind: String,
    val lat: Double,
    val lon: Double,
    val address: String?,
    val openingHoursRaw: String?,
    val schedule: WeeklySchedule,
    /** False when the hours are assumed from the chain (e.g. 7-Eleven) rather than published. */
    val hoursVerified: Boolean,
    val phone: String?,
    val website: String?,
)

object Geo {
    private const val EARTH_RADIUS_M = 6_371_000.0

    fun distanceMeters(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(2)
        return 2 * EARTH_RADIUS_M * asin(sqrt(a))
    }
}
