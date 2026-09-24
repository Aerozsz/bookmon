package my.kl.nightowl

import android.app.Application
import org.osmdroid.config.Configuration
import java.io.File

class NightOwlApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // OpenStreetMap-based map tiles: identify the app and keep the tile cache in app storage.
        Configuration.getInstance().apply {
            userAgentValue = "KLNightOwl/${BuildConfig.VERSION_NAME} (Android; $packageName)"
            osmdroidBasePath = File(cacheDir, "osmdroid")
            osmdroidTileCache = File(cacheDir, "osmdroid/tiles")
        }
    }
}
