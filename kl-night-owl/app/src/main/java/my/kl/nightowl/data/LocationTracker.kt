package my.kl.nightowl.data

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** The phone's position, used to sort by distance and to show "you are here" on the map. */
class LocationTracker(private val context: Context) {

    private val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> = _location.asStateFlow()
    private var listening = false

    // Every method is overridden on purpose: older Android versions have no defaults for them.
    private val listener = object : LocationListener {
        override fun onLocationChanged(location: Location) = offer(location)
        override fun onProviderEnabled(provider: String) = Unit
        override fun onProviderDisabled(provider: String) = Unit
        @Deprecated("Deprecated in Java")
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) = Unit
    }

    fun hasPermission(): Boolean =
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    fun start() {
        if (listening || !hasPermission()) return
        listening = true
        for (provider in listOf(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER)) {
            runCatching { manager.getLastKnownLocation(provider)?.let(::offer) }
            runCatching {
                if (manager.isProviderEnabled(provider)) {
                    manager.requestLocationUpdates(provider, 15_000L, 20f, listener, Looper.getMainLooper())
                }
            }
        }
    }

    fun stop() {
        if (!listening) return
        listening = false
        runCatching { manager.removeUpdates(listener) }
    }

    private fun offer(candidate: Location) {
        val current = _location.value
        val better = current == null ||
            candidate.time > current.time + 60_000 ||
            (candidate.time >= current.time - 10_000 && candidate.accuracy <= current.accuracy + 30f)
        if (better) _location.value = candidate
    }
}
