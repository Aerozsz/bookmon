#!/usr/bin/env bash
# Runs inside CI on a booted Android emulator (see .github/workflows/kl-night-owl.yml):
#   1. the on-device UI tests (debug build + NightOwlAppTest),
#   2. a smoke test of the exact release APK that gets published.
# Screenshots and a summary land in kl-night-owl/emulator-results/api<level>/.
set -u
API="$1"
ROOT=kl-night-owl
APKS="$ROOT/apks"
OUT="$ROOT/emulator-results/api$API"
PKG=my.kl.nightowl
SUMMARY="$OUT/api$API-summary.txt"
mkdir -p "$OUT"
status=0

log() { echo "$@" | tee -a "$SUMMARY"; }

log "== KL Night Owl on Android API $API ($(adb shell getprop ro.build.version.release | tr -d '\r'))"

# CI emulators are slow right after boot and their home-screen app can freeze behind an
# "isn't responding" dialog that covers everything. Tests don't need it: hide such dialogs,
# switch the launcher off and let boot-time work settle.
adb shell settings put global hide_error_dialogs 1 >/dev/null 2>&1 || true
adb shell pm disable-user --user 0 com.google.android.apps.nexuslauncher >/dev/null 2>&1 || true
adb shell pm disable-user --user 0 com.android.launcher3 >/dev/null 2>&1 || true
adb shell am broadcast -a android.intent.action.CLOSE_SYSTEM_DIALOGS >/dev/null 2>&1 || true
sleep 20

# Location on, emulator placed in central Kuala Lumpur (geo fix takes longitude first).
adb shell settings put secure location_mode 3 >/dev/null 2>&1 || true
adb shell cmd location set-location-enabled true >/dev/null 2>&1 || true
( for _ in $(seq 1 600); do adb emu geo fix 101.6953 3.1478 >/dev/null 2>&1; sleep 3; done ) &
GEO_PID=$!

log "== On-device UI tests (debug build)"
adb install -r -g "$APKS/app-debug.apk" | tee -a "$SUMMARY"
adb install -r "$APKS/app-debug-androidTest.apk" | tee -a "$SUMMARY"
adb logcat -c
adb shell am instrument -w my.kl.nightowl.test/androidx.test.runner.AndroidJUnitRunner | tr -d '\r' | tee "$OUT/instrumentation.txt"
cat "$OUT/instrumentation.txt" >> "$SUMMARY"
if grep -q "^OK (" "$OUT/instrumentation.txt"; then
  log "RESULT: on-device UI tests PASSED"
else
  log "RESULT: on-device UI tests FAILED"
  status=1
fi
adb logcat -d > "$OUT/logcat-tests.txt"
{
  echo "== Test log lines"
  grep -E "NightOwlTest|NightOwlRepo" "$OUT/logcat-tests.txt" | sed -E 's/^.*(NightOwlTest|NightOwlRepo): //'
  echo "== Crashes during tests"
  grep -A25 "FATAL EXCEPTION" "$OUT/logcat-tests.txt" | head -120
} >> "$SUMMARY"

for f in $(adb shell run-as "$PKG" ls files/screens 2>/dev/null | tr -d '\r'); do
  adb exec-out run-as "$PKG" cat "files/screens/$f" > "$OUT/$f"
done
log "Screenshots from UI tests: $(ls "$OUT" | grep -c '\.jpg$')"

log "== Smoke test of the release APK"
adb uninstall "$PKG" >/dev/null 2>&1
adb uninstall "$PKG.test" >/dev/null 2>&1
adb logcat -c
if adb install "$APKS"/KL-Night-Owl-*.apk | tee -a "$SUMMARY" | grep -q Success; then
  python3 "$ROOT/scripts/release_smoke.py" "$OUT" "$API" 2>&1 | tee -a "$SUMMARY"
  [ "${PIPESTATUS[0]}" -eq 0 ] || status=1
else
  log "RESULT: release APK failed to install"
  status=1
fi
adb logcat -d > "$OUT/logcat-release.txt"
if grep -q "FATAL EXCEPTION" "$OUT/logcat-release.txt"; then
  log "RESULT: release app CRASHED"
  grep -A40 "FATAL EXCEPTION" "$OUT/logcat-release.txt" | head -80 | tee -a "$SUMMARY"
  status=1
fi

kill "$GEO_PID" >/dev/null 2>&1
log "== Overall: $([ $status -eq 0 ] && echo PASSED || echo FAILED)"
exit $status
