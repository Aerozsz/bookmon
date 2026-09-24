package my.kl.nightowl.data

import android.content.Context
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import my.kl.nightowl.BuildConfig
import my.kl.nightowl.core.OsmParser
import my.kl.nightowl.core.OverpassQuery
import my.kl.nightowl.core.Place
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Where the places come from:
 *  1. the last successful download saved on the phone,
 *  2. otherwise the OpenStreetMap snapshot bundled in the app at build time,
 *  3. [refresh] downloads a fresh copy from the OpenStreetMap Overpass API.
 */
class PlacesRepository(private val context: Context) {

    enum class Source { BUNDLED, SAVED, LIVE }

    class Snapshot(
        val places: List<Place>,
        /** When OpenStreetMap produced the data (ISO-8601), if known. */
        val snapshotIso: String?,
        val source: Source,
        /** When the data was saved on this phone, or 0 for bundled data. */
        val savedAtMillis: Long,
    )

    private val cacheFile get() = File(context.filesDir, "overpass_cache.json")

    suspend fun loadOffline(): Snapshot? = withContext(Dispatchers.IO) { readSaved() ?: readBundled() }

    private fun readSaved(): Snapshot? = runCatching {
        val file = cacheFile
        if (!file.isFile) return@runCatching null
        val result = OsmParser.parse(file.readText())
        if (result.places.isEmpty()) null else Snapshot(result.places, result.snapshot, Source.SAVED, file.lastModified())
    }.getOrNull()

    private fun readBundled(): Snapshot? = runCatching {
        val text = context.assets.open(SEED_ASSET).bufferedReader().use { it.readText() }
        val result = OsmParser.parse(text)
        if (result.places.isEmpty()) null else Snapshot(result.places, result.snapshot, Source.BUNDLED, 0L)
    }.getOrNull()

    suspend fun refresh(): Snapshot = withContext(Dispatchers.IO) {
        val template = context.assets.open(QUERY_ASSET).bufferedReader().use { it.readText() }
        var lastError: Exception? = null
        // First ask for everything inside the Kuala Lumpur boundary; if a server can't resolve
        // the boundary, fall back to a rectangle around the city.
        for (useArea in listOf(true, false)) {
            val query = OverpassQuery.build(template, useArea)
            for (endpoint in OverpassQuery.ENDPOINTS) {
                try {
                    val body = post(endpoint, query)
                    val result = OsmParser.parse(body)
                    if (result.remark != null && result.elementCount == 0) throw IOException(result.remark)
                    if (result.elementCount == 0) break
                    if (result.places.isEmpty()) throw IOException("No night places in response")
                    val tmp = File(context.filesDir, "overpass_cache.tmp")
                    tmp.writeText(body)
                    if (!tmp.renameTo(cacheFile)) {
                        cacheFile.writeText(body)
                        tmp.delete()
                    }
                    return@withContext Snapshot(result.places, result.snapshot, Source.LIVE, System.currentTimeMillis())
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    lastError = e
                }
            }
        }
        throw IOException("Couldn't download data from OpenStreetMap", lastError)
    }

    private fun post(endpoint: String, query: String): String {
        val conn = URL(endpoint).openConnection() as HttpURLConnection
        try {
            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.connectTimeout = 20_000
            conn.readTimeout = 200_000
            conn.setRequestProperty("User-Agent", "KLNightOwl/${BuildConfig.VERSION_NAME} (Android app)")
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            conn.outputStream.use { it.write(("data=" + URLEncoder.encode(query, "UTF-8")).toByteArray()) }
            val code = conn.responseCode
            if (code != HttpURLConnection.HTTP_OK) throw IOException("HTTP $code from $endpoint")
            return conn.inputStream.bufferedReader().use { it.readText() }
        } finally {
            conn.disconnect()
        }
    }

    companion object {
        const val SEED_ASSET = "overpass_seed.json"
        const val QUERY_ASSET = "overpass_query.txt"
    }
}
