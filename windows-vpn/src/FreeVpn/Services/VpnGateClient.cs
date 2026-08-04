using System.Net.Http;
using System.Text;

namespace FreeVpn.Services;

/// <summary>
/// Fetches and parses the free public server list from VPN Gate,
/// an academic volunteer VPN project run by the University of Tsukuba, Japan.
/// The API returns a CSV where each row carries a complete, ready-to-use
/// OpenVPN configuration encoded as base64 in the final column.
/// </summary>
public sealed class VpnGateClient
{
    // Primary endpoint plus mirrors, tried in order for resilience.
    private static readonly string[] ApiUrls =
    {
        "https://www.vpngate.net/api/iphone/",
        "https://vpngate.net/api/iphone/",
        "http://www.vpngate.net/api/iphone/",
    };

    private readonly HttpClient _http;

    public VpnGateClient()
    {
        _http = new HttpClient { Timeout = TimeSpan.FromSeconds(30) };
        _http.DefaultRequestHeaders.UserAgent.ParseAdd("FreeVpn/1.0");
    }

    /// <summary>
    /// Downloads the live server list. Returns servers sorted best-first
    /// (highest score). Throws if every endpoint fails.
    /// </summary>
    public async Task<List<ServerInfo>> GetServersAsync(CancellationToken ct = default)
    {
        Exception? last = null;
        foreach (var url in ApiUrls)
        {
            try
            {
                var csv = await _http.GetStringAsync(url, ct);
                var servers = Parse(csv);
                if (servers.Count > 0)
                    // Fastest first: UDP servers ahead of TCP (much better throughput),
                    // then by the server's advertised speed.
                    return servers
                        .OrderByDescending(s => s.IsUdp)
                        .ThenByDescending(s => s.SpeedBps)
                        .ToList();
            }
            catch (Exception ex)
            {
                last = ex;
            }
        }
        throw new InvalidOperationException(
            "Could not reach the VPN Gate server list. Check your internet connection.", last);
    }

    private static List<ServerInfo> Parse(string csv)
    {
        var result = new List<ServerInfo>();
        var lines = csv.Split('\n');

        foreach (var raw in lines)
        {
            var line = raw.TrimEnd('\r');
            if (line.Length == 0) continue;
            // Skip the "*vpn_servers", header ("#..."), and trailing "*" markers.
            if (line[0] == '*' || line[0] == '#') continue;

            var f = line.Split(',');
            if (f.Length < 15) continue;

            var configB64 = f[14].Trim();
            if (configB64.Length == 0) continue;

            string config;
            try
            {
                config = Encoding.UTF8.GetString(Convert.FromBase64String(configB64));
            }
            catch (FormatException)
            {
                continue; // malformed row
            }

            if (!config.Contains("remote ", StringComparison.OrdinalIgnoreCase))
                continue;

            result.Add(new ServerInfo
            {
                HostName = f[0].Trim(),
                Ip = f[1].Trim(),
                Score = ParseLong(f[2]),
                Ping = (int)ParseLong(f[3]),
                SpeedBps = ParseLong(f[4]),
                CountryLong = f[5].Trim(),
                CountryShort = f[6].Trim(),
                Sessions = (int)ParseLong(f[7]),
                Protocol = config.Contains("proto tcp", StringComparison.OrdinalIgnoreCase) ? "TCP" : "UDP",
                OpenVpnConfig = config,
            });
        }

        return result;
    }

    private static long ParseLong(string s) =>
        long.TryParse(s.Trim(), out var v) ? v : 0;
}
