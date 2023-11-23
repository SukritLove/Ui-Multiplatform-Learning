package Login

import Home.HomeScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.core.component.KoinComponent
import Register.RegisterScreen
import androidx.compose.foundation.layout.Column
import cafe.adriel.voyager.navigator.Navigator
import data.UserData

class LoginScreen : Screen, KoinComponent {
    @OptIn(ExperimentalComposeUiApi::class, ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var passwordVisibility by remember { mutableStateOf(false) }

        val icon = if (passwordVisibility) painterResource("drawable/show.png")
        else painterResource("drawable/hide.png")



        Scaffold(topBar = {
            TopBar()
        }) {

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(painterResource("drawable/profile-user.png"), null, Modifier.size(100.dp))
                //Username Input
                Spacer(Modifier.padding(20.dp))
                CreateTextField(
                    label = "Username", value = username,
                    onValueChange = { username = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ), visualTransformation = VisualTransformation.None, trailingIcon = {})
                //Password Input
                Spacer(Modifier.padding(20.dp))
                CreateTextField(
                    label = "Password",
                    value = password,
                    onValueChange = { password = it },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility = !passwordVisibility
                        }) {
                            Icon(
                                painter = icon,
                                contentDescription = "Visibility Icon",
                                Modifier.size(20.dp)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
                //Login Button
                Spacer(Modifier.padding(20.dp))
                CreateButton(
                    modifier = Modifier.size(width = 200.dp, height = 50.dp),
                    btnName = "Login",
                    onClick = {
                        loginClick(
                            navigator = navigator,
                            username = username,
                            password = password
                        )
                    },
                )
                //Register Button
                Spacer(Modifier.padding(20.dp))
                CreateButton(
                    modifier = Modifier.size(width = 200.dp, height = 50.dp),
                    btnName = "Register",
                    onClick = {
                        navigator.push(RegisterScreen())
                    },
                )
            }
        }
    }
}


fun loginClick(navigator: Navigator, username: String, password: String) {
    val userData = UserData(username, password)
    navigator.push(HomeScreen(userData))
}


@Composable
fun CreateTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation,
    keyboardOptions: KeyboardOptions,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions, trailingIcon = trailingIcon,
    )
}


@Composable
fun CreateButton(modifier: Modifier = Modifier, btnName: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, focusedElevation = 10.dp)
    ) {
        Text(btnName)
    }
}


@Composable
fun TopBar() {
    TopAppBar(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(0.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Login",
                style = TextStyle(fontSize = 20.sp),
            )
        }
    }
}