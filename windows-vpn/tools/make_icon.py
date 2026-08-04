#!/usr/bin/env python3
"""Generate Assets/app.ico (a blue security shield) with no third-party deps.

Draws a 256x256 RGBA image in memory, encodes it as PNG using stdlib zlib,
and wraps it in a single-image ICO (Vista+ PNG-in-ICO format, which Windows
and the .NET build tooling both accept).
"""
import os
import struct
import zlib
import math

SIZE = 256


def blend(dst, src, a):
    return tuple(int(s * a + d * (1 - a)) for s, d in zip(src, dst))


def make_pixels():
    px = [[(0, 0, 0, 0) for _ in range(SIZE)] for _ in range(SIZE)]

    cx = SIZE / 2
    accent = (59, 130, 246)      # blue
    accent_dk = (37, 99, 235)
    white = (240, 244, 255)

    def shield_x_half(y):
        """Half-width of the shield at vertical position y (0..1 top->bottom)."""
        top, bot = 0.10, 0.94
        if y < top or y > bot:
            return -1
        t = (y - top) / (bot - top)
        # Wide flat top that tapers to a rounded point.
        if t < 0.62:
            w = 0.34
        else:
            tt = (t - 0.62) / (1 - 0.62)
            w = 0.34 * math.sqrt(max(0.0, 1 - tt * tt))
        # Slight rounding at the very top corners.
        if t < 0.08:
            w *= math.sqrt(max(0.0, 1 - ((0.08 - t) / 0.08) ** 2))
        return w

    for yy in range(SIZE):
        yn = yy / SIZE
        hw = shield_x_half(yn)
        if hw <= 0:
            continue
        half_px = hw * SIZE
        # Vertical gradient for a bit of depth.
        g = yn
        base = tuple(int(a * (1 - g) + b * g) for a, b in zip(accent, accent_dk))
        for xx in range(SIZE):
            dx = xx - cx
            edge = half_px - abs(dx)
            if edge <= 0:
                continue
            a = min(1.0, edge / 2.0)  # antialias edge
            px[yy][xx] = (*base, int(255 * a))

    # Draw a white keyhole in the centre.
    kx, ky = cx, SIZE * 0.44
    head_r = SIZE * 0.11
    for yy in range(SIZE):
        for xx in range(SIZE):
            if px[yy][xx][3] == 0:
                continue
            dx, dy = xx - kx, yy - ky
            in_head = dx * dx + dy * dy <= head_r * head_r
            in_stem = (abs(dx) <= SIZE * 0.045 and
                       ky <= yy <= ky + SIZE * 0.20)
            if in_head or in_stem:
                r, g, b, a = px[yy][xx]
                px[yy][xx] = (*white, a)
    return px


def png_bytes(px):
    raw = bytearray()
    for row in px:
        raw.append(0)  # filter type 0
        for (r, g, b, a) in row:
            raw += bytes((r, g, b, a))
    comp = zlib.compress(bytes(raw), 9)

    def chunk(typ, data):
        c = struct.pack(">I", len(data)) + typ + data
        crc = zlib.crc32(typ + data) & 0xFFFFFFFF
        return c + struct.pack(">I", crc)

    sig = b"\x89PNG\r\n\x1a\n"
    ihdr = struct.pack(">IIBBBBB", SIZE, SIZE, 8, 6, 0, 0, 0)  # 8-bit RGBA
    return sig + chunk(b"IHDR", ihdr) + chunk(b"IDAT", comp) + chunk(b"IEND", b"")


def ico_bytes(png):
    # ICONDIR + one ICONDIRENTRY pointing at the PNG payload.
    header = struct.pack("<HHH", 0, 1, 1)
    offset = 6 + 16
    entry = struct.pack(
        "<BBBBHHII",
        0, 0,            # width/height 0 => 256
        0, 0,            # colors, reserved
        1, 32,           # planes, bpp
        len(png), offset,
    )
    return header + entry + png


def main():
    here = os.path.dirname(os.path.abspath(__file__))
    out = os.path.join(here, "..", "src", "FreeVpn", "Assets", "app.ico")
    out = os.path.abspath(out)
    os.makedirs(os.path.dirname(out), exist_ok=True)
    png = png_bytes(make_pixels())
    with open(out, "wb") as f:
        f.write(ico_bytes(png))
    print("wrote", out, os.path.getsize(out), "bytes")


if __name__ == "__main__":
    main()
