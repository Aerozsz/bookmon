import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

fun loadProps(path: String) = Properties().apply {
    val f = rootProject.file(path)
    if (f.exists()) f.inputStream().use { load(it) }
}

val versionProps = loadProps("version.properties")
val localProps = loadProps("local.properties")

// Optional: a Google Maps SDK key switches the in-app map from OpenStreetMap to Google Maps.
// Set it as the MAPS_API_KEY environment variable (GitHub secret) or in local.properties.
val mapsApiKey: String = (System.getenv("MAPS_API_KEY") ?: localProps.getProperty("MAPS_API_KEY") ?: "").trim()

android {
    namespace = "my.kl.nightowl"
    compileSdk = 35

    defaultConfig {
        applicationId = "my.kl.nightowl"
        minSdk = 26
        targetSdk = 35
        versionCode = versionProps.getProperty("VERSION_CODE", "1").toInt()
        versionName = versionProps.getProperty("VERSION_NAME", "1.0.0")
        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
        buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
    }

    signingConfigs {
        // A fixed key so every build can be installed over the previous one.
        create("release") {
            storeFile = file("nightowl-release.p12")
            storeType = "PKCS12"
            storePassword = "nightowl-kl-2026"
            keyAlias = "nightowl"
            keyPassword = "nightowl-kl-2026"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}

tasks.withType<Test>().configureEach {
    testLogging {
        events("failed")
        showStandardStreams = true
        exceptionFormat = TestExceptionFormat.FULL
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")
    implementation(composeBom)
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    // In-app map: OpenStreetMap by default (no key needed), Google Maps when a key is provided.
    implementation("org.osmdroid:osmdroid-android:6.1.20")
    implementation("com.google.maps.android:maps-compose:6.4.1")
    implementation("com.google.android.gms:play-services-maps:19.0.0")

    testImplementation("junit:junit:4.13.2")
}
