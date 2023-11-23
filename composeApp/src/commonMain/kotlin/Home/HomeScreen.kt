package Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import data.UserData

class HomeScreen(/*username: String = "", password: String = "", */userData: UserData) : Screen {
    /*val username = userData.username
    val password = userData.password*/

    val username = userData.username
    val password = userData.password
    @Composable
    override fun Content() {

        CreateHomeScreen(username = username, password = password)
    }
}

@Composable
fun CreateHomeScreen(username: String, password: String) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome Back $username")
        Text("Your Password is $password")
    }

}