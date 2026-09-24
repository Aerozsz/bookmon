package my.kl.nightowl.data

import android.content.Context
import android.util.Log
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
import java.time.Duration
import java.time.Instant

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

    /** The newer of the saved download and the bundled snapshot (an app update may bring newer data). */
    suspend fun loadOffline(): Snapshot? = withContext(Dispatchers.IO) {
        listOfNotNull(readSaved(), readBundled()).maxByOrNull { it.snapshotIso.orEmpty() }
    }

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

    /**
     * Downloads the latest data. Mirrors can lag behind by weeks, so a copy older than [newerThan]
     * (the snapshot already shown) is skipped. Returns null when no server has anything newer.
     */
    suspend fun refresh(newerThan: String? = null): Snapshot? = withContext(Dispatchers.IO) {
        val template = context.assets.open(QUERY_ASSET).bufferedReader().use { it.readText() }
        var lastError: Exception? = null
        var onlyOlderCopies = false
        val deadline = System.currentTimeMillis() + REFRESH_BUDGET_MS
        // Only places inside the Kuala Lumpur boundary. If every server is busy, keep the current data.
        servers@ for (useArea in listOf(true)) {
            val query = OverpassQuery.build(template, useArea)
            for (endpoint in OverpassQuery.ENDPOINTS) {
                if (System.currentTimeMillis() > deadline) {
                    Log.w(TAG, "refresh: out of time, keeping current data")
                    break@servers
                }
                val started = System.currentTimeMillis()
                try {
                    val body = post(endpoint, query)
                    val result = OsmParser.parse(body)
                    Log.i(
                        TAG,
                        "refresh: $endpoint area=$useArea -> ${result.elementCount} elements, " +
                            "${result.places.size} places, snapshot ${result.snapshot} in ${System.currentTimeMillis() - started} ms",
                    )
                    if (result.remark != null && result.elementCount == 0) throw IOException(result.remark)
                    if (result.elementCount == 0) continue // this server can't resolve the boundary
                    if (result.places.isEmpty()) throw IOException("No night places in response")
                    val snapshot = result.snapshot
                    if (newerThan != null && snapshot != null && snapshot < newerThan) {
                        onlyOlderCopies = true
                        // A copy a few days behind means ours is current enough: stop searching.
                        if (daysBetween(snapshot, newerThan) <= CLOSE_ENOUGH_DAYS) break@servers
                        continue
                    }
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
                    Log.w(TAG, "refresh: $endpoint area=$useArea failed after ${System.currentTimeMillis() - started} ms: $e")
                    lastError = e
                }
            }
        }
        if (onlyOlderCopies) return@withContext null
        throw IOException("Couldn't download data from OpenStreetMap", lastError)
    }

    private fun post(endpoint: String, query: String): String {
        val conn = URL(endpoint).openConnection() as HttpURLConnection
        try {
            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.connectTimeout = 15_000
            conn.readTimeout = 60_000
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

    private fun daysBetween(olderIso: String, newerIso: String): Long = runCatching {
        Duration.between(Instant.parse(olderIso), Instant.parse(newerIso)).toDays()
    }.getOrDefault(Long.MAX_VALUE)

    companion object {
        private const val CLOSE_ENOUGH_DAYS = 3L
        const val SEED_ASSET = "overpass_seed.json"
        const val QUERY_ASSET = "overpass_query.txt"
        private const val TAG = "NightOwlRepo"

        /** A refresh never keeps the spinner going longer than this; the current data stays in use. */
        private const val REFRESH_BUDGET_MS = 150_000L
    }
}
