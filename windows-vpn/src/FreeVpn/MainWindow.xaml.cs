using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using FreeVpn.Services;

namespace FreeVpn;

public partial class MainWindow : Window
{
    private readonly VpnGateClient _client = new();
    private readonly OpenVpnRunner _vpn = new();
    private ServerInfo? _connectedServer;
    private List<ServerInfo> _allServers = new();

    public MainWindow()
    {
        InitializeComponent();

        _vpn.StateChanged += s => Dispatcher.Invoke(() => OnStateChanged(s));
        _vpn.LogLine += line => Dispatcher.Invoke(() => AppendLog(line));

        ServerList.SelectionChanged += (_, _) => UpdateConnectButton();

        Loaded += async (_, _) => await LoadServersAsync();
        Closing += (_, _) => _vpn.Dispose();
    }

    private async Task LoadServersAsync()
    {
        LoadingPanel.Visibility = Visibility.Visible;
        LoadingText.Text = "Loading free servers…";
        ServerList.ItemsSource = null;
        RefreshButton.IsEnabled = false;
        CountText.Text = "";

        try
        {
            _allServers = await _client.GetServersAsync();
            LoadingPanel.Visibility = Visibility.Collapsed;
            ApplyFilter();
        }
        catch (Exception ex)
        {
            LoadingText.Text = ex.Message;
            AppendLog("ERROR: " + ex.Message);
        }
        finally
        {
            RefreshButton.IsEnabled = true;
            UpdateConnectButton();
        }
    }

    /// <summary>Applies the country filter and chosen sort to the loaded servers.</summary>
    private void ApplyFilter()
    {
        if (_allServers.Count == 0) return;

        var q = (FilterBox?.Text ?? "").Trim();
        IEnumerable<ServerInfo> view = _allServers;

        if (q.Length > 0)
        {
            view = view.Where(s =>
                s.CountryLong.Contains(q, StringComparison.OrdinalIgnoreCase) ||
                s.CountryShort.Contains(q, StringComparison.OrdinalIgnoreCase));
        }

        // Default order is already fastest-first (UDP then speed) from the client.
        // "Sort by ping" re-orders to lowest latency first — best for nearby servers.
        if (SortByPing?.IsChecked == true)
            view = view.Where(s => s.Ping > 0).OrderBy(s => s.Ping)
                       .Concat(view.Where(s => s.Ping <= 0));

        var list = view.ToList();
        ServerList.ItemsSource = list;
        CountText.Text = q.Length > 0
            ? $"{list.Count} of {_allServers.Count} servers"
            : $"{list.Count} free servers available";
        if (list.Count > 0)
            ServerList.SelectedIndex = 0;
    }

    private void OnFilterChanged(object sender, TextChangedEventArgs e)
    {
        if (FilterHint != null)
            FilterHint.Visibility = string.IsNullOrEmpty(FilterBox.Text)
                ? Visibility.Visible : Visibility.Collapsed;
        ApplyFilter();
    }

    private void OnSortChanged(object sender, RoutedEventArgs e) => ApplyFilter();

    private async void OnRefresh(object sender, RoutedEventArgs e)
    {
        if (_vpn.State is VpnState.Connected or VpnState.Connecting) return;
        await LoadServersAsync();
    }

    private async void OnConnectClick(object sender, RoutedEventArgs e)
    {
        if (_vpn.State is VpnState.Connected or VpnState.Connecting)
        {
            _vpn.Disconnect();
            return;
        }

        if (ServerList.SelectedItem is not ServerInfo server)
            return;

        _connectedServer = server;
        LogBox.Clear();
        await _vpn.ConnectAsync(server);
    }

    private void OnStateChanged(VpnState state)
    {
        switch (state)
        {
            case VpnState.Disconnected:
                SetStatus("Disconnected", (Brush)FindResource("MutedBrush"));
                ConnectButton.Content = "Connect";
                ConnectButton.Background = (Brush)FindResource("AccentBrush");
                RefreshButton.IsEnabled = true;
                ServerList.IsEnabled = true;
                break;
            case VpnState.Connecting:
                SetStatus("Connecting…", (Brush)FindResource("AccentBrush"));
                ConnectButton.Content = "Cancel";
                RefreshButton.IsEnabled = false;
                ServerList.IsEnabled = false;
                break;
            case VpnState.Connected:
                var where = _connectedServer is null ? "" : $" — {_connectedServer.CountryLong}";
                SetStatus("Connected" + where, (Brush)FindResource("OkBrush"));
                ConnectButton.Content = "Disconnect";
                ConnectButton.Background = (Brush)FindResource("ErrBrush");
                RefreshButton.IsEnabled = false;
                ServerList.IsEnabled = false;
                break;
            case VpnState.Error:
                SetStatus("Connection failed", (Brush)FindResource("ErrBrush"));
                ConnectButton.Content = "Connect";
                ConnectButton.Background = (Brush)FindResource("AccentBrush");
                RefreshButton.IsEnabled = true;
                ServerList.IsEnabled = true;
                break;
        }
        UpdateConnectButton();
    }

    private void SetStatus(string text, Brush brush)
    {
        StatusText.Text = text;
        StatusDot.Fill = brush;
    }

    private void UpdateConnectButton()
    {
        if (_vpn.State is VpnState.Connected or VpnState.Connecting)
        {
            ConnectButton.IsEnabled = true;
            return;
        }
        ConnectButton.IsEnabled = ServerList.SelectedItem is ServerInfo;
    }

    private void AppendLog(string line)
    {
        LogBox.AppendText(line + Environment.NewLine);
        LogBox.ScrollToEnd();
    }
}
