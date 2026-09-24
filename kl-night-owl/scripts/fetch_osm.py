#!/usr/bin/env python3
"""Download Kuala Lumpur night-venue candidates from OpenStreetMap (Overpass API).

Used by the CI build so each APK ships with fresh data. Uses the same query template
as the app (app/src/main/assets/overpass_query.txt).

Usage: fetch_osm.py <output.json>
Writes GitHub Actions outputs `fresh` (true/false) and `commit` (true when the committed
copy is missing or more than 7 days old).
"""
import datetime
import json
import os
import sys
import time
import urllib.parse
import urllib.request

HERE = os.path.dirname(os.path.abspath(__file__))
TEMPLATE = os.path.join(HERE, "..", "app", "src", "main", "assets", "overpass_query.txt")
ENDPOINTS = [
    "https://overpass-api.de/api/interpreter",
    "https://overpass.private.coffee/api/interpreter",
    "https://maps.mail.ru/osm/tools/overpass/api/interpreter",
    "https://overpass.kumi.systems/api/interpreter",
]
KL_AREA = 'area["ISO3166-2"="MY-14"]->.kl;'
KL_BBOX = "(3.03,101.60,3.26,101.77)"
KEEP = {
    "name", "name:en", "brand", "operator", "amenity", "shop", "cuisine", "opening_hours",
    "phone", "contact:phone", "website", "contact:website", "access",
}


def build_query(use_area):
    with open(TEMPLATE, encoding="utf-8") as f:
        template = f.read()
    return (template.replace("{{AREA}}", KL_AREA if use_area else "")
            .replace("{{SCOPE}}", "(area.kl)" if use_area else KL_BBOX))


def post(endpoint, query):
    body = urllib.parse.urlencode({"data": query}).encode()
    req = urllib.request.Request(endpoint, data=body, headers={
        "User-Agent": "KLNightOwl-build/1.0 (GitHub Actions)",
        "Content-Type": "application/x-www-form-urlencoded",
    })
    with urllib.request.urlopen(req, timeout=240) as resp:
        return json.loads(resp.read().decode("utf-8"))


def fetch():
    for use_area in (True, False):
        query = build_query(use_area)
        for endpoint in ENDPOINTS:
            for attempt in range(2):
                try:
                    print(f"Querying {endpoint} (area={use_area}, attempt {attempt + 1})", flush=True)
                    data = post(endpoint, query)
                    n = len(data.get("elements", []))
                    print(f"  -> {n} elements, remark={data.get('remark')!r}", flush=True)
                    if n == 0 and data.get("remark"):
                        raise RuntimeError(data["remark"])
                    if n == 0:
                        break  # boundary not found here: try the bounding box
                    return data
                except Exception as e:  # noqa: BLE001 - try the next server
                    print(f"  !! {e}", flush=True)
                    time.sleep(5)
            else:
                continue
            if use_area:
                break
    return None


def slim(data):
    out = []
    for el in data["elements"]:
        tags = {k: v for k, v in el.get("tags", {}).items() if k in KEEP or k.startswith("addr:")
                or k.startswith("disused:") or k.startswith("abandoned:") or k.startswith("was:")}
        item = {"type": el["type"], "id": el["id"], "tags": tags}
        if "lat" in el:
            item["lat"], item["lon"] = el["lat"], el["lon"]
        elif "center" in el:
            item["center"] = el["center"]
        else:
            continue
        out.append(item)
    return {
        "version": data.get("version", 0.6),
        "osm3s": {"timestamp_osm_base": data.get("osm3s", {}).get("timestamp_osm_base")},
        "elements": out,
    }


def snapshot_age_days(path):
    try:
        with open(path, encoding="utf-8") as f:
            ts = json.load(f)["osm3s"]["timestamp_osm_base"]
        made = datetime.datetime.fromisoformat(ts.replace("Z", "+00:00"))
        return (datetime.datetime.now(datetime.timezone.utc) - made).days
    except Exception:  # noqa: BLE001
        return None


def set_output(name, value):
    path = os.environ.get("GITHUB_OUTPUT")
    if path:
        with open(path, "a", encoding="utf-8") as f:
            f.write(f"{name}={value}\n")


def main():
    out_path = sys.argv[1]
    age = snapshot_age_days(out_path)
    data = fetch()
    if data is None:
        print("::warning::Could not download fresh OpenStreetMap data; the app keeps the committed snapshot.")
        set_output("fresh", "false")
        set_output("commit", "false")
        return
    slimmed = slim(data)
    with open(out_path, "w", encoding="utf-8") as f:
        json.dump(slimmed, f, ensure_ascii=False, separators=(",", ":"))
    print(f"Wrote {len(slimmed['elements'])} elements ({os.path.getsize(out_path) // 1024} KB), "
          f"snapshot {slimmed['osm3s']['timestamp_osm_base']}; previous copy age: {age} days")
    set_output("fresh", "true")
    set_output("commit", "true" if age is None or age >= 7 else "false")


if __name__ == "__main__":
    main()
