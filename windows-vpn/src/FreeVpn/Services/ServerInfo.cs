using System.Text;

namespace FreeVpn.Services;

/// <summary>
/// One free VPN relay server as published by the VPN Gate public API.
/// </summary>
public sealed class ServerInfo
{
    public string HostName { get; init; } = "";
    public string Ip { get; init; } = "";
    public long Score { get; init; }
    public int Ping { get; init; }
    public long SpeedBps { get; init; }
    public string CountryLong { get; init; } = "";
    public string CountryShort { get; init; } = "";
    public int Sessions { get; init; }

    /// <summary>Raw text of a ready-to-use OpenVPN config file.</summary>
    public string OpenVpnConfig { get; init; } = "";

    /// <summary>Download speed formatted as Mbps, e.g. "42.3 Mbps".</summary>
    public string SpeedMbps => $"{SpeedBps / 1_000_000.0:0.0} Mbps";

    /// <summary>Ping formatted for display, "-" when unknown.</summary>
    public string PingText => Ping > 0 ? $"{Ping} ms" : "-";

    /// <summary>Two-letter country code mapped to a flag emoji.</summary>
    public string Flag => CountryToFlag(CountryShort);

    public string Display => $"{Flag}  {CountryLong}";

    private static string CountryToFlag(string code)
    {
        if (string.IsNullOrWhiteSpace(code) || code.Length != 2)
            return "🏳️";

        code = code.ToUpperInvariant();
        var sb = new StringBuilder();
        foreach (char c in code)
        {
            if (c < 'A' || c > 'Z') return "🏳️";
            // Regional indicator symbols start at U+1F1E6 for 'A'.
            sb.Append(char.ConvertFromUtf32(0x1F1E6 + (c - 'A')));
        }
        return sb.ToString();
    }
}
