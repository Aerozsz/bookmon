"""
VFS Global LIFT API client — built from decompiled APK analysis.

Endpoints are from the VFS Italy Android app (lift-api.vfsglobal.com).
Login uses form-encoded POST. Appointment checks use JSON POST.
"""

import base64
import datetime
import logging
import time

import requests
from cryptography.hazmat.primitives import hashes, serialization
from cryptography.hazmat.primitives.asymmetric import padding

logger = logging.getLogger(__name__)


# ── RSA public key (fallback) ────────────────────────────────────────
#
# Extracted from apk-analysis/resources/res/values/strings.xml →
# <string name="public_key">. This is a 1024-bit RSA SubjectPublicKeyInfo
# in bare Base64 DER (no PEM headers). k.java tries SharedPreferences
# first (populated by the runtime fetch from rsa_public_key_end_point)
# and falls back to this static value.
FALLBACK_RSA_PUBLIC_KEY_B64 = (
    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpigN3/5Ti/WJk51pbPQdpCe96"
    "TPVoeMAk/cUlAPpYh8zGpr6zssbM11Je1SoQTiuipxIL+c0oGXti8vLzln3yfS+N"
    "56wuSh0Hyt1Z+waSx6IDFlfzImEtq8m1osS32B83HRiFZbeKB8QIRJhZil1pJSzM"
    "sg0Y0QmDyv1yR4FzIQIDAQAB"
)

# Runtime key fetch (from remote_config_defaults.xml → app_config_prod)
RSA_KEY_FETCH_URL = "https://litf-api.vfsglobal.com/data/D14906B6BA614C8C97747C352AA409D1"
RSA_KEY_FETCH_BEARER = "B14C8275AE58C1856515E10E6A450D97E128B29768EEFDC469D16D8"


# ── RSA-OAEP-SHA256/MGF1 encryption ───────────────────────────────────
#
# The VFS Italy Android app encrypts the login password AND a fresh
# "mobile;<iso-timestamp>" ClientSource header value before every
# protected call. The cipher is:
#
#   Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
#   + OAEPParameterSpec(SHA-256, "MGF1", MGF1ParameterSpec.SHA256,
#                       PSpecified.DEFAULT)
#
# Output is Base64-encoded with all whitespace stripped. See:
#   apk-analysis/com/vfs/italyglobal/utilities/k.java  (the cipher)
#   apk-analysis/com/vfs/italyglobal/utilities/Utility.java::e  (wrapper)
#   apk-analysis/com/vfs/italyglobal/activities/AppointmentLogin.java:329

def _load_public_key(key_material: str):
    """
    Load a public key from either a full PEM block or a bare Base64
    DER SubjectPublicKeyInfo (which is the format used by the hardcoded
    fallback key in strings.xml).
    """
    material = key_material.strip()
    if "BEGIN PUBLIC KEY" in material:
        return serialization.load_pem_public_key(material.encode("utf-8"))
    # Bare base64 DER — strip whitespace and decode.
    der = base64.b64decode("".join(material.split()))
    return serialization.load_der_public_key(der)


def rsa_encrypt(public_key_material: str, plaintext: str) -> str:
    """Encrypt a string with RSA-OAEP-SHA256/MGF1 and Base64-encode."""
    if not public_key_material:
        raise ValueError("No RSA public key provided.")
    key = _load_public_key(public_key_material)
    ciphertext = key.encrypt(
        plaintext.encode("utf-8"),
        padding.OAEP(
            mgf=padding.MGF1(algorithm=hashes.SHA256()),
            algorithm=hashes.SHA256(),
            label=None,
        ),
    )
    # Android strips whitespace from the Base64 output before sending.
    return base64.b64encode(ciphertext).decode("ascii").replace(" ", "").replace("\n", "")


def build_client_source(public_key_material: str) -> str:
    """
    Build the ClientSource header value.

    Plaintext format (from Utility.l()): "mobile;<ISO-8601 UTC timestamp>"
    The timestamp is regenerated on every request, so this must be called
    fresh before each protected API call.
    """
    ts = datetime.datetime.utcnow().strftime("%Y-%m-%dT%H:%M:%SZ")
    return rsa_encrypt(public_key_material, f"mobile;{ts}")


def fetch_rsa_public_key(timeout: int = 15) -> str | None:
    """
    Fetch a fresh RSA public key from the VFS endpoint.

    Mirrors AppointmentLogin.java::H1 — POST to rsaPublicKeyEndPoint with
    a hardcoded Bearer token, response body is {"data": "<base64-der>"}.
    Returns None on any failure so the caller can fall back to the
    bundled key.
    """
    try:
        resp = requests.post(
            RSA_KEY_FETCH_URL,
            headers={
                "Authorization": f"Bearer {RSA_KEY_FETCH_BEARER}",
                "User-Agent": "okhttp/4.12.0",
                "Accept": "application/json",
            },
            timeout=timeout,
        )
        if resp.status_code != 200:
            logger.warning("RSA key fetch HTTP %d: %s", resp.status_code, resp.text[:200])
            return None
        data = resp.json()
        key = data.get("data") if isinstance(data, dict) else None
        if key:
            logger.info("Fetched fresh RSA public key from %s", RSA_KEY_FETCH_URL)
            return key
        logger.warning("RSA key fetch: no 'data' field in response: %s", str(data)[:200])
    except Exception as exc:
        logger.warning("RSA key fetch failed: %s", exc)
    return None

# ── API Base URLs (from remote_config_defaults.xml → app_config_prod) ──
LIFT_API_BASE = "https://lift-api.vfsglobal.com"

# ── Endpoints (from Retrofit interface ab.b) ──────────────────────────
ENDPOINTS = {
    # Auth (form-encoded POST)
    "login":                f"{LIFT_API_BASE}/user/login",
    "logout":               f"{LIFT_API_BASE}/user/logout",

    # Appointment booking flow (JSON POST)
    "check_slot":           f"{LIFT_API_BASE}/appointment/CheckIsSlotAvailable",
    "calendar":             f"{LIFT_API_BASE}/appointment/calendar",
    "timeslot":             f"{LIFT_API_BASE}/appointment/timeslot",

    # Master data (GET)
    "centers":              f"{LIFT_API_BASE}/master/center/{{mission}}/{{country}}",
    "visa_categories":      f"{LIFT_API_BASE}/master/visacategory/{{mission}}/{{country}}/{{center}}",
    "sub_visa_categories":  f"{LIFT_API_BASE}/master/subvisacategory/{{mission}}/{{country}}/{{center}}/{{visacategorycode}}",
}

# Headers matching what the VFS Italy Android app sends on every protected
# request (discovered by reading the HashMap construction in
# AddApplicantBookAppointmentActivity.java lines 710-727 / 832-850).
#
# Key points:
#   - Auth header is "Authorize" (NOT "Authorization"), raw token, no "Bearer".
#   - origin / referer are the web portal URL, even though the app calls the
#     mobile API.
#   - cfmlift is "mobile" (from remote_config_defaults.xml →
#     app_config_prod.appointment_cfm_lift).
#   - ClientSource is RSA-OAEP-SHA256 encrypted "mobile;<iso-timestamp>",
#     base64, spaces stripped. It is refreshed before every authenticated
#     request (see _refresh_client_source). Android's Utility.e() catches
#     encryption exceptions and returns "", so if the public key is not
#     configured we fall back to an empty header.
APPOINTMENT_ORIGIN = "https://visa.vfsglobal.com/"

DEFAULT_HEADERS = {
    "User-Agent": "okhttp/4.12.0",
    "Accept": "application/json",
    "Accept-Language": "en-US,en;q=0.9",
    "origin": APPOINTMENT_ORIGIN,
    "referer": APPOINTMENT_ORIGIN,
    "cfmlift": "mobile",
    "ClientSource": "",
}


class VFSClient:
    """
    VFS Global LIFT API client.

    Handles authentication and appointment availability checking using
    the exact endpoints and payload formats from the VFS Italy APK.
    """

    def __init__(self, email: str, password: str, country_code: str,
                 mission_code: str, vac_code: str,
                 captcha_api_key: str = "",
                 rsa_public_key_pem: str = ""):
        self.email = email
        self.password = password
        self.country_code = country_code.lower()
        self.mission_code = mission_code.lower()
        self.vac_code = vac_code
        self.captcha_api_key = captcha_api_key

        # Resolve the RSA public key in the same order as the Android app:
        #   1. An explicit override (from config / env)
        #   2. A fresh key fetched from the VFS key endpoint
        #   3. The hardcoded fallback bundled in strings.xml
        if rsa_public_key_pem:
            self.rsa_public_key_pem = rsa_public_key_pem
            logger.info("Using RSA public key from configuration.")
        else:
            fetched = fetch_rsa_public_key()
            if fetched:
                self.rsa_public_key_pem = fetched
            else:
                logger.info("Using bundled fallback RSA public key.")
                self.rsa_public_key_pem = FALLBACK_RSA_PUBLIC_KEY_B64

        self.session = requests.Session()
        self.session.headers.update(DEFAULT_HEADERS)
        self.token: str | None = None
        self.token_expires: float = 0

    # ── Crypto helpers ───────────────────────────────────────────────

    def _encrypt(self, plaintext: str) -> str:
        """RSA-encrypt a value with the configured public key."""
        return rsa_encrypt(self.rsa_public_key_pem, plaintext)

    def _refresh_client_source(self) -> None:
        """
        Recompute the ClientSource header with a fresh timestamp.

        Every protected API call rebuilds this — see
        AppointmentGetEarliestDates.java:970 where ClientSource is set
        right before each HashMap is passed to Retrofit.
        """
        try:
            self.session.headers["ClientSource"] = build_client_source(
                self.rsa_public_key_pem
            )
        except ValueError as exc:
            # No key configured — leave the header empty and hope the
            # server tolerates it (Utility.e() catches exceptions and
            # returns "" on the Android side).
            logger.warning("ClientSource not encrypted: %s", exc)
            self.session.headers["ClientSource"] = ""

    # ── Authentication ───────────────────────────────────────────────

    def login(self) -> bool:
        """
        Authenticate with VFS LIFT API directly.

        The APK uses @FormUrlEncoded POST to user/login with fields:
        username, password, missioncode, countrycode

        `password` is RSA-OAEP-SHA256 encrypted with the VFS public key
        and then Base64-encoded (see AppointmentLogin.java::F1 line 329).
        The `ClientSource` header is a fresh encrypted
        "mobile;<iso-timestamp>" — regenerated on every request.
        """
        try:
            encrypted_password = self._encrypt(self.password)
        except Exception as exc:
            logger.error("Failed to RSA-encrypt password: %s", exc)
            return False

        self._refresh_client_source()

        form_data = {
            "username": self.email,
            "password": encrypted_password,
            "missioncode": self.mission_code,
            "countrycode": self.country_code,
        }

        logger.info("Logging in to LIFT API as %s ...", self.email)

        try:
            resp = self.session.post(
                ENDPOINTS["login"],
                data=form_data,  # form-encoded, NOT json=
                headers={
                    **self.session.headers,
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                timeout=30,
            )
        except requests.RequestException as exc:
            logger.error("Login request failed: %s", exc)
            return False

        logger.info("Login response: HTTP %d", resp.status_code)
        logger.debug("Login body: %s", resp.text[:500])

        if resp.status_code != 200:
            logger.error("Login failed: HTTP %d — %s", resp.status_code, resp.text[:500])
            return False

        try:
            data = resp.json()
        except Exception:
            logger.error("Login response is not JSON: %s", resp.text[:300])
            return False

        # Extract token — try multiple known field locations
        self.token = (
            data.get("accessToken")
            or data.get("token")
            or data.get("data", {}).get("accessToken")
            or data.get("data", {}).get("token")
            or data.get("Token")
            or data.get("access_token")
        )

        if not self.token:
            logger.error("Login response (no token found): %s", data)
            return False

        self.token_expires = time.time() + 20 * 60
        # NOTE: mobile API uses "Authorize" header (no "Bearer" prefix),
        # not the standard "Authorization" header.
        self.session.headers["Authorize"] = self.token
        logger.info("LIFT API login successful — token acquired.")
        return True

    def set_token(self, token: str):
        """Set an externally-obtained token (from browser_login.py)."""
        self.token = token
        self.token_expires = time.time() + 20 * 60
        self.session.headers["Authorize"] = token

    def ensure_authenticated(self) -> bool:
        """Re-login if token has expired or is missing."""
        if self.token and time.time() < self.token_expires:
            return True
        return self.login()

    # ── Appointment Availability ─────────────────────────────────────

    def check_slot_available(self, visa_category: str) -> dict:
        """
        POST appointment/CheckIsSlotAvailable

        Request (from CheckIsSlotAvailableRequestParams):
        {
            "countryCode": "dza",
            "missionCode": "ita",
            "vacCode": "ALG",
            "visaCategoryCode": "TOUR",
            "roleName": "Individual",
            "loginUser": "user@email.com"
        }

        Response (from CheckIsSlotAvailableResModel):
        {
            "earliestDate": "2024-03-15",
            "earliestSlotLists": [{"applicant": "...", "date": "..."}],
            "error": null
        }
        """
        if not self.ensure_authenticated():
            return {"available": False, "slots": [], "raw": {}, "error": "Authentication failed"}

        payload = {
            "countryCode": self.country_code,
            "missionCode": self.mission_code,
            "vacCode": self.vac_code,
            "visaCategoryCode": visa_category,
            "roleName": "Individual",
            "loginUser": self.email,
        }

        return self._post_appointment(ENDPOINTS["check_slot"], payload)

    def get_calendar(self, visa_category: str) -> dict:
        """POST appointment/calendar — get available calendar dates."""
        if not self.ensure_authenticated():
            return {"available": False, "slots": [], "raw": {}, "error": "Authentication failed"}

        payload = {
            "countryCode": self.country_code,
            "missionCode": self.mission_code,
            "vacCode": self.vac_code,
            "visaCategoryCode": visa_category,
            "loginUser": self.email,
        }

        return self._post_appointment(ENDPOINTS["calendar"], payload)

    def get_timeslots(self, visa_category: str, date: str) -> dict:
        """POST appointment/timeslot — get time slots for a specific date."""
        if not self.ensure_authenticated():
            return {"available": False, "slots": [], "raw": {}, "error": "Authentication failed"}

        payload = {
            "countryCode": self.country_code,
            "missionCode": self.mission_code,
            "vacCode": self.vac_code,
            "visaCategoryCode": visa_category,
            "loginUser": self.email,
            "appointmentDate": date,
        }

        return self._post_appointment(ENDPOINTS["timeslot"], payload)

    # ── Master Data ──────────────────────────────────────────────────

    def get_centers(self) -> list:
        """GET master/center/{mission}/{country}"""
        url = ENDPOINTS["centers"].format(
            mission=self.mission_code, country=self.country_code
        )
        return self._get_json(url)

    def get_visa_categories(self) -> list:
        """GET master/visacategory/{mission}/{country}/{center}"""
        url = ENDPOINTS["visa_categories"].format(
            mission=self.mission_code, country=self.country_code,
            center=self.vac_code
        )
        return self._get_json(url)

    # ── Internal Helpers ─────────────────────────────────────────────

    def _get_json(self, url: str) -> list:
        """GET request that returns JSON list."""
        self._refresh_client_source()
        try:
            resp = self.session.get(url, timeout=30)
            logger.debug("GET %s — HTTP %d", url, resp.status_code)
            if resp.status_code == 200:
                return resp.json()
            else:
                logger.error("GET %s — HTTP %d: %s", url, resp.status_code, resp.text[:300])
        except Exception as exc:
            logger.error("GET %s failed: %s", url, exc)
        return []

    def _post_appointment(self, url: str, payload: dict) -> dict:
        """Make a JSON POST to an appointment endpoint with auth retry."""
        logger.debug("POST %s — %s", url, payload)

        # Every protected call needs a fresh encrypted ClientSource
        # because the plaintext contains an ISO timestamp the server
        # may reject if stale.
        self._refresh_client_source()

        try:
            resp = self.session.post(url, json=payload, timeout=30)
        except requests.RequestException as exc:
            logger.error("Request failed: %s", exc)
            return {"available": False, "slots": [], "raw": {}, "error": str(exc)}

        # Handle token expiry
        if resp.status_code == 401:
            logger.warning("Token expired, re-authenticating...")
            self.token = None
            if not self.login():
                return {"available": False, "slots": [], "raw": {}, "error": "Re-auth failed"}
            self._refresh_client_source()
            try:
                resp = self.session.post(url, json=payload, timeout=30)
            except requests.RequestException as exc:
                return {"available": False, "slots": [], "raw": {}, "error": str(exc)}

        if resp.status_code != 200:
            logger.error("HTTP %d — %s", resp.status_code, resp.text[:500])
            return {
                "available": False, "slots": [], "raw": {},
                "error": f"HTTP {resp.status_code}: {resp.text[:200]}",
            }

        try:
            data = resp.json()
        except Exception:
            return {"available": False, "slots": [], "raw": {}, "error": f"Non-JSON response: {resp.text[:200]}"}

        return self._parse_availability(data)

    def _parse_availability(self, data: dict) -> dict:
        """
        Parse VFS response for slot availability.

        Known response format (CheckIsSlotAvailableResModel):
        {
            "earliestDate": "2024-03-15",
            "earliestSlotLists": [{"applicant": "...", "date": "..."}],
            "error": {"errorCode": "...", "errorMsg": "..."}
        }
        """
        result = {"available": False, "slots": [], "raw": data, "error": None}

        if not isinstance(data, dict):
            if isinstance(data, list) and len(data) > 0:
                result["available"] = True
                result["slots"] = data
            return result

        # Check for API error
        error = data.get("error")
        if error and isinstance(error, dict):
            error_msg = error.get("errorMsg") or error.get("description") or ""
            if error_msg:
                result["error"] = error_msg
                logger.warning("API error: %s", error_msg)

        # CheckIsSlotAvailable response
        earliest_date = data.get("earliestDate")
        earliest_slots = data.get("earliestSlotLists")

        if earliest_date:
            result["available"] = True
            result["slots"] = [{"date": earliest_date}]
            if earliest_slots and isinstance(earliest_slots, list):
                result["slots"] = earliest_slots
            return result

        # Timeslot response — check remainingSeats
        if isinstance(data.get("data"), list):
            available_slots = [
                s for s in data["data"]
                if isinstance(s, dict) and s.get("remainingSeats", 0) > 0
            ]
            if available_slots:
                result["available"] = True
                result["slots"] = available_slots
            return result

        # Generic: any non-empty slot list
        for key in ("slots", "SlotDetails", "timeSlot", "data"):
            val = data.get(key)
            if isinstance(val, list) and len(val) > 0:
                result["available"] = True
                result["slots"] = val
                return result

        return result
