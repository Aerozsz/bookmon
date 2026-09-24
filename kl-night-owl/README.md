# 🦉 KL Night Owl (Android)

A standalone Android app, unrelated to the rest of this repository. It lists every shop,
restaurant, konbini (convenience store), mamak, petrol station shop, pharmacy and bar in
Kuala Lumpur that is open at some point between **midnight and 6 AM**.

- **List view:** name, what the place is (e.g. "Mamak restaurant · Indian, Malaysian"), whether
  it's open now and until when, tonight's hours, and the full week's opening/closing times.
  Filter by category, "Open now" or "All night", search, and sort by distance.
- **Map view:** an interactive map of every place. Tap a dot, then **Start route in Google Maps**
  to open Google Maps with the route. Numbered bubbles are groups of places; tap one to zoom in.

## Install

Download the latest `KL-Night-Owl-x.y.z.apk` from this repository's
[Releases page](https://github.com/Aerozsz/bookmon/releases) (look for "KL Night Owl") on
your phone, open it and tap **Install**. Android may ask you to allow installing apps from your
browser.

## Where the data comes from

Places and opening hours come from [OpenStreetMap](https://www.openstreetmap.org) through the
Overpass API (© OpenStreetMap contributors, ODbL):

- Every build downloads a fresh snapshot and bundles it, so the app works offline straight away.
- The ⟳ button (or pulling the list down) downloads the latest data on the phone. The app also
  refreshes by itself when its data is more than 3 days old.
- Chains that are open round the clock in KL (7-Eleven, FamilyMart, KK Mart, Lawson, CU,
  Petronas/Shell/Petron/Caltex/BHP stations, Nasi Kandar Pelita, Ali Maju) are included even when
  OpenStreetMap lists no hours for them. They are labelled "usually 24h".

The OpenStreetMap query is in `app/src/main/assets/overpass_query.txt`. The app and the build
script both use it.

## Map tiles

By default, the in-app map uses OpenStreetMap data with CARTO tiles, which need no account or
key. To draw the in-app map with Google Maps instead, create a Maps SDK for Android key in
Google Cloud and add it as a repository secret named `MAPS_API_KEY`. The next build will use it.
Directions always open in the Google Maps app, with or without a key.

## Build

GitHub Actions builds this app on every push that touches `kl-night-owl/`
(`.github/workflows/kl-night-owl.yml`). The workflow runs the unit tests, builds a signed release
APK and publishes it as a GitHub Release. To build it yourself, open `kl-night-owl/` in Android
Studio, or run `./gradlew :app:assembleRelease` with the Android SDK installed.

The signing key (`app/nightowl-release.p12`) is in the repository on purpose. Every build is
signed with it, so updates install over the previous version. It is only for installing the app
yourself, not for publishing on an app store.
