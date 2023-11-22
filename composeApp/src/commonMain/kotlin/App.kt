
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import screen.LoginScreen

@Composable
fun App() {
    MaterialTheme(colors = MaterialTheme.colors.copy(primary = Color.Black)) {
        Navigator(LoginScreen())
    }
}

