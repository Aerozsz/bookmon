package my.kl.nightowl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.kl.nightowl.core.Category
import my.kl.nightowl.ui.theme.AllDayContainer
import my.kl.nightowl.ui.theme.ClosedRose
import my.kl.nightowl.ui.theme.ClosedRoseContainer
import my.kl.nightowl.ui.theme.OpenGreen
import my.kl.nightowl.ui.theme.OpenGreenContainer
import my.kl.nightowl.ui.theme.StreetlightAmber
import java.util.Locale

val Category.color: Color get() = Color(colorArgb)

@Composable
fun CategoryBadge(category: Category, size: Dp = 42.dp) {
    Box(
        modifier = Modifier
            .size(size)
            .background(category.color.copy(alpha = 0.18f), CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(category.emoji, fontSize = (size.value * 0.48f).sp)
    }
}

@Composable
fun StatusPill(ui: PlaceUi, modifier: Modifier = Modifier) {
    val always = ui.place.schedule.isAlwaysOpen
    val (bg, fg, label) = when {
        always -> Triple(AllDayContainer, StreetlightAmber, "24h")
        ui.openNow -> Triple(OpenGreenContainer, OpenGreen, "Open")
        else -> Triple(ClosedRoseContainer, ClosedRose, "Closed")
    }
    Text(
        text = label,
        color = fg,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .background(bg, RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 4.dp),
    )
}

fun formatDistance(meters: Double?): String? = when {
    meters == null -> null
    meters < 1000 -> "${(meters / 10).toInt() * 10} m"
    meters < 10_000 -> String.format(Locale.US, "%.1f km", meters / 1000)
    else -> "${(meters / 1000).toInt()} km"
}
