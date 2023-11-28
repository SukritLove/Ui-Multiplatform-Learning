package Login

import Home.HomeScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.core.component.KoinComponent
import AddContact.AddContactScreen
import DriverFactory
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import app.cash.sqldelight.db.SqlDriver
import cafe.adriel.voyager.navigator.Navigator
import com.example.Database
import com.russhwolf.settings.Settings
import createDatabase
import data.UserData
import data.UserDataRepo
import data.UserDataRepoImplement
import data.UserDataStore
import model.Model
import org.jetbrains.skia.Data


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
            Model.TopBar(title = "Login", modifier = Modifier.fillMaxWidth())
        }) {

            LazyColumn(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                item {
                    Spacer(Modifier.padding(20.dp))
                    Image(
                        painterResource("drawable/profile-user.png"),
                        null,
                        Modifier.size(100.dp)
                    )
                }
                //Username Input
                item {
                    Spacer(Modifier.padding(20.dp))
                    CreateTextField(
                        modifier = Modifier.width(300.dp),
                        label = "Username", value = username,
                        onValueChange = { username = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ), visualTransformation = VisualTransformation.None, trailingIcon = {})
                }
                //Password Input
                item {
                    Spacer(Modifier.padding(20.dp))
                    CreateTextField(
                        modifier = Modifier.width(300.dp),
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
                }
                //Login Button
                item {
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
                }
                //Register Button
                item {
                    Spacer(Modifier.padding(20.dp))
                    CreateButton(
                        modifier = Modifier.size(width = 200.dp, height = 50.dp),
                        btnName = "Add Contact",
                        onClick = {
                            navigator.push(AddContactScreen())
                        },
                    )
                    Spacer(Modifier.padding(20.dp))
                }
            }
        }
    }


}


fun loginClick(navigator: Navigator, username: String, password: String) {
    UserDataStore.userData = UserData(username, password)
    navigator.push(HomeScreen())
}


@Composable
fun CreateTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation,
    keyboardOptions: KeyboardOptions,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier,
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


