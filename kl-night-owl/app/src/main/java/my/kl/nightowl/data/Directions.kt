package my.kl.nightowl.data

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import my.kl.nightowl.core.Place
import java.util.Locale

enum class TravelMode(val label: String, val urlValue: String) {
    DRIVE("Drive", "driving"),
    WALK("Walk", "walking"),
    TRANSIT("Transit", "transit"),
}

/** Opens Google Maps with a route from the current position to [place]. */
fun openDirections(context: Context, place: Place, mode: TravelMode) {
    val destination = String.format(Locale.US, "%.6f,%.6f", place.lat, place.lon)
    val uri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=$destination&travelmode=${mode.urlValue}")
    val intent = Intent(Intent.ACTION_VIEW, uri).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        context.startActivity(Intent(intent).setPackage(GOOGLE_MAPS_PACKAGE))
    } catch (e: ActivityNotFoundException) {
        // Google Maps app not installed: the same link opens Google Maps in the browser.
        try {
            context.startActivity(intent)
        } catch (e2: ActivityNotFoundException) {
            Toast.makeText(context, "Install Google Maps to get directions", Toast.LENGTH_LONG).show()
        }
    }
}

fun dial(context: Context, phone: String) {
    val number = phone.split(';').first().trim()
    runCatching { context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }
}

fun openWebsite(context: Context, url: String) {
    val fixed = if (url.startsWith("http")) url else "https://$url"
    runCatching { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(fixed)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }
}

private const val GOOGLE_MAPS_PACKAGE = "com.google.android.apps.maps"
