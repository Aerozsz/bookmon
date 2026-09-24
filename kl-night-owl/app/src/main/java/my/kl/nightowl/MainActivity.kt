package my.kl.nightowl

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import my.kl.nightowl.ui.NightOwlScreen
import my.kl.nightowl.ui.NightOwlViewModel
import my.kl.nightowl.ui.theme.NightOwlTheme

class MainActivity : ComponentActivity() {

    private val viewModel: NightOwlViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        setContent {
            NightOwlTheme {
                NightOwlScreen(viewModel)
            }
        }
    }
}
