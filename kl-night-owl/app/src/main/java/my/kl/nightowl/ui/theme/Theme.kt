package my.kl.nightowl.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val StreetlightAmber = Color(0xFFFFC857)
val OpenGreen = Color(0xFF7BD88F)
val OpenGreenContainer = Color(0xFF16311F)
val ClosedRose = Color(0xFFFF8FA3)
val ClosedRoseContainer = Color(0xFF3A1A22)
val AllDayContainer = Color(0xFF3A2E0A)

private val NightColors = darkColorScheme(
    primary = StreetlightAmber,
    onPrimary = Color(0xFF2B1D00),
    primaryContainer = Color(0xFF3D2F00),
    onPrimaryContainer = Color(0xFFFFE08A),
    secondary = Color(0xFF8FA8FF),
    onSecondary = Color(0xFF0B1A4A),
    secondaryContainer = Color(0xFF26325A),
    onSecondaryContainer = Color(0xFFDDE3FF),
    tertiary = Color(0xFFFF9EC0),
    onTertiary = Color(0xFF4A0F24),
    background = Color(0xFF0A0E1A),
    onBackground = Color(0xFFE6E8F0),
    surface = Color(0xFF0A0E1A),
    onSurface = Color(0xFFE6E8F0),
    surfaceVariant = Color(0xFF1C2336),
    onSurfaceVariant = Color(0xFFB4BACB),
    surfaceContainerLowest = Color(0xFF070A13),
    surfaceContainerLow = Color(0xFF10152A),
    surfaceContainer = Color(0xFF141A2E),
    surfaceContainerHigh = Color(0xFF1B2238),
    surfaceContainerHighest = Color(0xFF232B43),
    outline = Color(0xFF4A5370),
    outlineVariant = Color(0xFF2A3147),
    error = Color(0xFFFF6B6B),
)

@Composable
fun NightOwlTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = NightColors, content = content)
}
