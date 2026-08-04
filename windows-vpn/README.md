# Free VPN — Windows

A free, no-signup VPN client for Windows with a simple graphical interface.
It connects through the **[VPN Gate](https://www.vpngate.net/)** public relay
network — an academic volunteer VPN project run by the University of Tsukuba,
Japan — so there are no accounts, subscriptions, or servers for you to set up.

**OpenVPN** and the **Wintun** driver are bundled inside the app, so there is
nothing else to install.

<p align="center"><i>Pick a country → click Connect. That's it.</i></p>

---

## Download & run

Grab the latest build from the **[Releases page](../../releases)**:

| File | What it is |
|------|------------|
| `FreeVpn-Setup-x.y.z.exe` | Installer (recommended) — adds Start-menu/desktop shortcuts |
| `FreeVpn-Portable-x.y.z.zip` | Portable — unzip anywhere and run `FreeVpn.exe` |

The app asks for administrator rights when it starts. This is required so it
can create the virtual network adapter and set up routing — the same thing
every VPN does. Windows may also show a one-time SmartScreen prompt because
the build isn't code-signed; choose **More info → Run anyway**.

### Using it

1. The server list loads automatically (free servers, sorted best-first).
2. Click **Refresh servers** any time to pull a fresh list.
3. Select a location and click **Connect**.
4. When the status pill turns green (**Connected**), your traffic is tunnelled.
5. Click **Disconnect** when you're done.

Open the **Connection log** at the bottom if you want to see what OpenVPN is
doing under the hood.

---

## How it works

```
┌────────────┐   fetch server list    ┌──────────────────────┐
│  Free VPN  │ ─────────────────────► │  VPN Gate public API │
│  (WPF GUI) │ ◄───────────────────── │  (free relay servers)│
└─────┬──────┘   OpenVPN configs      └──────────────────────┘
      │ launches (as admin)
      ▼
┌────────────┐   encrypted tunnel     ┌──────────────────────┐
│ openvpn.exe│ ═════════════════════► │  chosen relay server │ ──► Internet
│ + wintun   │                        └──────────────────────┘
└────────────┘
```

- `VpnGateClient` downloads the live CSV server list from VPN Gate. Each row
  carries a complete, ready-to-use OpenVPN config (inline certs and keys).
- `OpenVpnRunner` writes the chosen config to a temp file and launches the
  bundled `openvpn.exe`, forcing the bundled Wintun driver
  (`--windows-driver wintun`) so no legacy TAP driver install is needed.
- The GUI watches OpenVPN's output for `Initialization Sequence Completed`
  to flip to the **Connected** state.

## Project layout

```
windows-vpn/
├─ src/FreeVpn/            WPF app (.NET 8)
│  ├─ MainWindow.xaml      the GUI
│  ├─ Services/
│  │  ├─ VpnGateClient.cs  fetch + parse the free server list
│  │  ├─ OpenVpnRunner.cs  drive the bundled openvpn.exe
│  │  └─ ServerInfo.cs     server model
│  └─ app.manifest         requests administrator rights
├─ installer/FreeVpn.iss   Inno Setup installer script
├─ tools/make_icon.py      generates the app icon (no deps)
└─ ../.github/workflows/vpn-build.yml   builds everything on Windows CI
```

## Building it yourself

You need Windows with the **.NET 8 SDK**. From `windows-vpn/`:

```powershell
# 1. Build the app
dotnet publish src/FreeVpn/FreeVpn.csproj -c Release -r win-x64 --self-contained true -o publish

# 2. Add OpenVPN (copy its bin folder) + Wintun next to the app
#    (the CI workflow does this automatically — see vpn-build.yml)
mkdir publish\openvpn
copy "C:\Program Files\OpenVPN\bin\*" publish\openvpn\
copy path\to\wintun.dll publish\openvpn\

# 3. Run
publish\FreeVpn.exe
```

The GitHub Actions workflow (`.github/workflows/vpn-build.yml`) automates all of
this on a Windows runner and publishes the portable ZIP and the installer to a
GitHub Release. Trigger it from the **Actions** tab (**Run workflow**) or by
pushing a `v*` tag.

## A few honest notes

- VPN Gate servers are run by **volunteers**. They're great for bypassing
  geo-blocks, using public Wi-Fi safely, and general privacy — but they are
  **not** meant for anything highly sensitive, and speeds/availability vary by
  server. That's the trade-off for being genuinely free. Pick a low-ping,
  high-speed server for the best experience, and Refresh if one is flaky.
- VPN Gate logs connection metadata per its academic
  [policy](https://www.vpngate.net/en/). Read it if that matters to you.
- Use it lawfully and in line with VPN Gate's terms.

## Credits & licenses

- **[VPN Gate](https://www.vpngate.net/)** — University of Tsukuba, Japan.
- **[OpenVPN](https://openvpn.net/)** — GPLv2, bundled unmodified.
- **[Wintun](https://www.wintun.net/)** — by the WireGuard project.

This client's own source is provided as-is for personal use.
