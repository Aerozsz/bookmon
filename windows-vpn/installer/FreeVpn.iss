; Inno Setup script for Free VPN.
; Packages the self-contained publish output (app + bundled OpenVPN + Wintun)
; into a standard Windows installer.
;
; Expected layout (produced by the CI build):
;   {#SourceDir}\FreeVpn.exe
;   {#SourceDir}\openvpn\openvpn.exe, *.dll, wintun.dll
;   {#SourceDir}\... (other .NET runtime files, if any)

#ifndef SourceDir
  #define SourceDir "..\publish"
#endif
#ifndef AppVersion
  #define AppVersion "1.0.0"
#endif

#define AppName "Free VPN"
#define AppExe "FreeVpn.exe"
#define AppPublisher "Free VPN (VPN Gate client)"

[Setup]
AppId={{4C2F1A6E-9D3B-4E77-8B21-FREE0VPN0001}
AppName={#AppName}
AppVersion={#AppVersion}
AppPublisher={#AppPublisher}
DefaultDirName={autopf}\FreeVpn
DefaultGroupName={#AppName}
UninstallDisplayIcon={app}\{#AppExe}
OutputBaseFilename=FreeVpn-Setup-{#AppVersion}
Compression=lzma2
SolidCompression=yes
WizardStyle=modern
; The app itself requires admin (creates the VPN adapter), so install
; machine-wide into Program Files.
PrivilegesRequired=admin
ArchitecturesInstallIn64BitMode=x64compatible
ArchitecturesAllowed=x64compatible

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "Create a &desktop shortcut"; GroupDescription: "Additional icons:"

[Files]
Source: "{#SourceDir}\*"; DestDir: "{app}"; Flags: recursesubdirs createallsubdirs ignoreversion

[Icons]
Name: "{group}\{#AppName}"; Filename: "{app}\{#AppExe}"
Name: "{group}\Uninstall {#AppName}"; Filename: "{uninstallexe}"
Name: "{autodesktop}\{#AppName}"; Filename: "{app}\{#AppExe}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#AppExe}"; Description: "Launch {#AppName}"; Flags: nowait postinstall skipifsilent
