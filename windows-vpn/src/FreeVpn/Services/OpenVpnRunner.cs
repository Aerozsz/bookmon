using System.Diagnostics;
using System.IO;
using System.Text;

namespace FreeVpn.Services;

public enum VpnState
{
    Disconnected,
    Connecting,
    Connected,
    Error,
}

/// <summary>
/// Drives the bundled openvpn.exe. The app ships a portable copy of OpenVPN
/// together with the Wintun driver (wintun.dll), so no system install is
/// required. Because the app runs elevated (see app.manifest), the OpenVPN
/// child process can create the virtual adapter and edit routes directly.
/// </summary>
public sealed class OpenVpnRunner : IDisposable
{
    private Process? _process;
    private string? _configPath;
    private readonly object _lock = new();

    public event Action<string>? LogLine;
    public event Action<VpnState>? StateChanged;

    public VpnState State { get; private set; } = VpnState.Disconnected;

    /// <summary>Directory that holds openvpn.exe, its DLLs, and wintun.dll.</summary>
    public static string OpenVpnDir =>
        Path.Combine(AppContext.BaseDirectory, "openvpn");

    public static string OpenVpnExe =>
        Path.Combine(OpenVpnDir, "openvpn.exe");

    /// <summary>tapctl.exe ships in the OpenVPN bin folder; it creates adapters.</summary>
    public static string TapCtlExe =>
        Path.Combine(OpenVpnDir, "tapctl.exe");

    /// <summary>Name of the dedicated Wintun adapter this app creates and reuses.</summary>
    private const string AdapterName = "FreeVPN";

    public static bool IsInstalled => File.Exists(OpenVpnExe);

    public async Task ConnectAsync(ServerInfo server, CancellationToken ct = default)
    {
        Disconnect();

        if (!IsInstalled)
        {
            SetState(VpnState.Error);
            Log("ERROR: openvpn.exe not found next to the app. The bundle is incomplete.");
            return;
        }

        SetState(VpnState.Connecting);
        Log($"Preparing connection to {server.CountryLong} ({server.Ip})...");

        // OpenVPN on Windows does not create the virtual adapter itself; it
        // expects one to already exist. Create (once) a dedicated Wintun
        // adapter with the bundled tapctl.exe, then reuse it on later connects.
        if (!await EnsureAdapterAsync(ct))
        {
            SetState(VpnState.Error);
            return;
        }

        // Write the config to a private temp file.
        var dir = Path.Combine(Path.GetTempPath(), "FreeVpn");
        Directory.CreateDirectory(dir);
        _configPath = Path.Combine(dir, "current.ovpn");
        var cfg = HardenConfig(server.OpenVpnConfig);
        await File.WriteAllTextAsync(_configPath, cfg, ct);

        var psi = new ProcessStartInfo
        {
            FileName = OpenVpnExe,
            WorkingDirectory = OpenVpnDir,
            UseShellExecute = false,
            CreateNoWindow = true,
            RedirectStandardOutput = true,
            RedirectStandardError = true,
            StandardOutputEncoding = Encoding.UTF8,
            StandardErrorEncoding = Encoding.UTF8,
        };
        psi.ArgumentList.Add("--config");
        psi.ArgumentList.Add(_configPath);
        // Use the TAP-Windows6 adapter we created above, by name.
        psi.ArgumentList.Add("--windows-driver");
        psi.ArgumentList.Add("tap-windows6");
        psi.ArgumentList.Add("--dev-node");
        psi.ArgumentList.Add(AdapterName);
        psi.ArgumentList.Add("--verb");
        psi.ArgumentList.Add("3");

        var proc = new Process { StartInfo = psi, EnableRaisingEvents = true };
        proc.OutputDataReceived += (_, e) => OnLine(e.Data);
        proc.ErrorDataReceived += (_, e) => OnLine(e.Data);
        proc.Exited += (_, _) =>
        {
            if (State != VpnState.Disconnected)
            {
                Log("OpenVPN process exited.");
                SetState(VpnState.Disconnected);
            }
        };

        lock (_lock)
        {
            _process = proc;
        }

        proc.Start();
        proc.BeginOutputReadLine();
        proc.BeginErrorReadLine();
    }

    private void OnLine(string? line)
    {
        if (string.IsNullOrEmpty(line)) return;
        Log(line);

        if (line.Contains("Initialization Sequence Completed", StringComparison.OrdinalIgnoreCase))
        {
            SetState(VpnState.Connected);
        }
        else if (line.Contains("AUTH_FAILED", StringComparison.OrdinalIgnoreCase)
                 || line.Contains("Cannot load inline certificate", StringComparison.OrdinalIgnoreCase)
                 || line.Contains("Exiting due to fatal error", StringComparison.OrdinalIgnoreCase))
        {
            SetState(VpnState.Error);
        }
    }

    public void Disconnect()
    {
        Process? p;
        lock (_lock)
        {
            p = _process;
            _process = null;
        }

        if (p is not null)
        {
            try
            {
                if (!p.HasExited)
                {
                    p.Kill(entireProcessTree: true);
                    p.WaitForExit(5000);
                }
            }
            catch { /* best effort */ }
            finally
            {
                p.Dispose();
            }
        }

        if (State != VpnState.Disconnected)
            SetState(VpnState.Disconnected);
    }

    /// <summary>Folder holding the bundled TAP-Windows6 driver package.</summary>
    private static string DriverDir => Path.Combine(OpenVpnDir, "driver");

    /// <summary>
    /// Makes sure a TAP-Windows6 adapter named <see cref="AdapterName"/> exists,
    /// installing the bundled driver and creating the adapter if needed.
    /// Returns false (and logs) on failure.
    /// </summary>
    private async Task<bool> EnsureAdapterAsync(CancellationToken ct)
    {
        if (!File.Exists(TapCtlExe))
        {
            Log("ERROR: tapctl.exe is missing from the bundle; cannot create the network adapter.");
            return false;
        }

        // Reuse the adapter if a previous run already created it.
        var (listCode, listOut) = await RunCaptureAsync(TapCtlExe, new[] { "list" }, ct);
        if (listCode == 0 && listOut.Contains(AdapterName, StringComparison.OrdinalIgnoreCase))
        {
            Log($"Using existing network adapter \"{AdapterName}\".");
            return true;
        }

        // First run: stage the TAP driver into Windows' driver store with pnputil
        // so the adapter can actually be created. Wintun has no standalone driver
        // package, so we use the classic TAP-Windows6 driver, which does.
        var inf = FindTapInf();
        if (inf is null)
        {
            Log("ERROR: bundled TAP driver (.inf) not found; cannot set up the network adapter.");
            return false;
        }

        Log("Installing the network driver (first run only, may take a few seconds)...");
        var (pnpCode, pnpOut) = await RunCaptureAsync(
            "pnputil.exe", new[] { "/add-driver", inf, "/install" }, ct);
        // pnputil returns non-zero in some already-installed cases; that's fine —
        // only a failed tapctl create below is fatal.
        if (pnpCode != 0)
            Log($"(driver install returned {pnpCode}; continuing)");

        Log($"Creating network adapter \"{AdapterName}\"...");
        var (createCode, createOut) = await RunCaptureAsync(
            TapCtlExe, new[] { "create", "--name", AdapterName }, ct);

        if (createCode != 0)
        {
            foreach (var l in (pnpOut + "\n" + createOut).Split('\n'))
                if (l.Trim().Length > 0) Log("setup: " + l.Trim());
            Log("ERROR: Could not create the network adapter. " +
                "Make sure the app is running as administrator.");
            return false;
        }

        Log($"Network adapter \"{AdapterName}\" ready.");
        return true;
    }

    /// <summary>Locates the bundled TAP-Windows6 .inf inside the driver folder.</summary>
    private static string? FindTapInf()
    {
        if (!Directory.Exists(DriverDir)) return null;
        var infs = Directory.GetFiles(DriverDir, "*.inf", SearchOption.AllDirectories);
        // Prefer a path that mentions "tap" (the tap-windows6 driver folder).
        return infs.FirstOrDefault(f => f.Contains("tap", StringComparison.OrdinalIgnoreCase))
               ?? infs.FirstOrDefault();
    }

    /// <summary>Runs a console tool to completion and returns its exit code and combined output.</summary>
    private static async Task<(int code, string output)> RunCaptureAsync(
        string exe, string[] args, CancellationToken ct)
    {
        var psi = new ProcessStartInfo
        {
            FileName = exe,
            WorkingDirectory = OpenVpnDir,
            UseShellExecute = false,
            CreateNoWindow = true,
            RedirectStandardOutput = true,
            RedirectStandardError = true,
        };
        foreach (var a in args) psi.ArgumentList.Add(a);

        using var p = new Process { StartInfo = psi };
        var sb = new StringBuilder();
        p.OutputDataReceived += (_, e) => { if (e.Data != null) sb.AppendLine(e.Data); };
        p.ErrorDataReceived += (_, e) => { if (e.Data != null) sb.AppendLine(e.Data); };
        p.Start();
        p.BeginOutputReadLine();
        p.BeginErrorReadLine();
        await p.WaitForExitAsync(ct);
        return (p.ExitCode, sb.ToString());
    }

    /// <summary>
    /// Ensures the config plays well with an unattended, GUI-driven launch:
    /// no interactive prompts, sane defaults. VPN Gate configs already carry
    /// inline certs and keys, so we mostly add resilience directives.
    /// </summary>
    private static string HardenConfig(string config)
    {
        var sb = new StringBuilder(config);
        sb.AppendLine();
        sb.AppendLine("# --- added by Free VPN ---");
        // Retry name resolution instead of dying if DNS lags at startup.
        if (!config.Contains("resolv-retry"))
            sb.AppendLine("resolv-retry infinite");
        if (!config.Contains("nobind"))
            sb.AppendLine("nobind");
        // Don't cache credentials interactively (public servers need none).
        if (!config.Contains("auth-nocache"))
            sb.AppendLine("auth-nocache");
        // Keep the tunnel alive.
        if (!config.Contains("keepalive"))
            sb.AppendLine("keepalive 10 60");
        // OpenVPN 2.6 dropped several implicit cipher defaults. Many VPN Gate
        // relays are older and only advertise CBC ciphers, so widen the
        // negotiable set (and provide a fallback) to avoid handshake failures.
        if (!config.Contains("data-ciphers"))
        {
            sb.AppendLine("data-ciphers AES-256-GCM:AES-128-GCM:CHACHA20-POLY1305:AES-256-CBC:AES-128-CBC");
            sb.AppendLine("data-ciphers-fallback AES-128-CBC");
        }
        return sb.ToString();
    }

    private void SetState(VpnState s)
    {
        State = s;
        StateChanged?.Invoke(s);
    }

    private void Log(string msg) => LogLine?.Invoke(msg);

    public void Dispose() => Disconnect();
}
