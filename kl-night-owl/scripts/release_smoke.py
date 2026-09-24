#!/usr/bin/env python3
"""Black-box smoke test of the release APK on an emulator, the way a first-time user meets it.

Launches the app fresh, answers the location prompt, checks the list appears, taps "Go",
opens the map and taps it, and fails if the app dies. Screenshots go to <out>/.
Usage: release_smoke.py <out_dir> <api_level>
"""
import os
import re
import subprocess
import sys
import time
import xml.etree.ElementTree as ET

OUT, API = sys.argv[1], sys.argv[2]
PKG = "my.kl.nightowl"
ALLOW_IDS = {
    "com.android.permissioncontroller:id/permission_allow_foreground_only_button",
    "com.android.permissioncontroller:id/permission_allow_button",
    "com.android.packageinstaller:id/permission_allow_button",
}
failures = []


def adb(*args):
    return subprocess.run(["adb", *args], capture_output=True, text=True, timeout=120)


def dump():
    adb("shell", "uiautomator", "dump", "/sdcard/ui.xml")
    xml = adb("shell", "cat", "/sdcard/ui.xml").stdout
    start = xml.find("<?xml")
    if start < 0:
        return None
    try:
        return ET.fromstring(xml[start:])
    except ET.ParseError:
        return None


def nodes(root):
    return list(root.iter("node")) if root is not None else []


def text(n):
    return (n.get("text") or "") + " " + (n.get("content-desc") or "")


def center(n):
    x1, y1, x2, y2 = map(int, re.findall(r"\d+", n.get("bounds")))
    return (x1 + x2) // 2, (y1 + y2) // 2


def tap_xy(x, y):
    adb("shell", "input", "tap", str(x), str(y))


def tap(n):
    tap_xy(*center(n))


def wait_for(pred, timeout):
    end = time.time() + timeout
    while time.time() < end:
        root = dump()
        if root is not None and pred(root):
            return root
        time.sleep(2)
    return None


def shot(name):
    png = subprocess.run(["adb", "exec-out", "screencap", "-p"], capture_output=True, timeout=60).stdout
    with open(os.path.join(OUT, f"api{API}_release_{name}.png"), "wb") as f:
        f.write(png)


def alive():
    return adb("shell", "pidof", PKG).stdout.strip() != ""


def resumed_activity():
    out = adb("shell", "dumpsys", "activity", "activities").stdout
    for line in out.splitlines():
        if "ResumedActivity" in line:
            return line.strip()
    return "?"


def check(ok, message):
    print(("PASS " if ok else "FAIL ") + message, flush=True)
    if not ok:
        failures.append(message)


adb("shell", "am", "broadcast", "-a", "android.intent.action.CLOSE_SYSTEM_DIALOGS")
adb("shell", "am", "start", "-W", "-n", f"{PKG}/.MainActivity")

# 1. First launch asks for location.
root = wait_for(lambda r: any(n.get("resource-id") in ALLOW_IDS for n in nodes(r)), 40)
check(root is not None, "location permission prompt appears on first launch")
if root is not None:
    shot("00_permission_prompt")
    allow = [n for n in nodes(root) if n.get("resource-id") in ALLOW_IDS]
    tap(allow[0])

# 2. The list of night places shows up.
root = wait_for(lambda r: any("Tonight:" in text(n) for n in nodes(r)), 90)
check(root is not None, "list of night places is shown")
if root is not None:
    check(any("KL Night Owl" in text(n) for n in nodes(root)), "app title is shown")
    check(any("spots open between midnight" in text(n) for n in nodes(root)), "place count subtitle is shown")
    time.sleep(2)
    shot("01_list")

    # 3. "Go" hands the route to Google Maps (or the browser when Maps isn't installed).
    go = [n for n in nodes(root) if (n.get("text") or "") == "Go"]
    check(bool(go), "a Go button is shown on the cards")
    if go:
        tap(go[0])
        time.sleep(5)
        activity = resumed_activity()
        print("After Go, foreground: " + activity, flush=True)
        shot("02_after_go")
        if "com.google.android.apps.maps" in activity:
            # A fresh emulator shows Google Maps' first-run screens; step past them to the route.
            for _ in range(5):
                screen = dump()
                buttons = [n for n in nodes(screen) if (n.get("text") or "").strip().lower()
                           in ("skip", "no thanks", "not now", "while using the app", "allow", "only this time", "got it")]
                if not buttons:
                    break
                tap(buttons[0])
                time.sleep(5)
            time.sleep(4)
            shot("02b_google_maps_route")
            screen = dump()
            seen = sorted({(n.get("text") or "").strip() for n in nodes(screen) if (n.get("text") or "").strip()})
            print("Google Maps shows: " + " | ".join(seen[:40]), flush=True)
        left_app = PKG + "/" not in activity
        check(left_app or alive(), "Go opens a map/browser app without crashing ours")
        if left_app:
            adb("shell", "input", "keyevent", "KEYCODE_BACK")
            time.sleep(2)
        adb("shell", "am", "start", "-n", f"{PKG}/.MainActivity")
        time.sleep(3)

# 4. The map tab shows the interactive map.
root = wait_for(lambda r: any((n.get("text") or "") == "Map" for n in nodes(r)), 30)
check(root is not None, "Map tab is reachable")
if root is not None:
    map_tab = max((n for n in nodes(root) if (n.get("text") or "") == "Map"), key=lambda n: center(n)[1])
    tap(map_tab)
    time.sleep(12)  # tiles download
    shot("03_map")
    size = re.findall(r"(\d+)x(\d+)", adb("shell", "wm", "size").stdout)[-1]
    w, h = int(size[0]), int(size[1])
    tap_xy(w // 2, int(h * 0.55))  # most likely a group bubble around the city centre
    time.sleep(4)
    shot("04_map_after_tap")
    check(alive(), "app still running after using the map")

check(alive(), "app process alive at the end")
print("RESULT: release smoke test " + ("PASSED" if not failures else "FAILED: " + "; ".join(failures)), flush=True)
sys.exit(1 if failures else 0)
