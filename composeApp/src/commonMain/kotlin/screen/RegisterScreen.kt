package screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class RegisterScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
//        val coroutineScope = rememberCoroutineScope()
//        val pageCount = 2
//        val pagerState = rememberPagerState(pageCount = { pageCount })


        LazyColumn {
            item { Text("Register") }
            item {
                Button(onClick = {
                    navigator.push(LoginScreen())
                }) {
                    Text("Back To Login")
                }
            }
        }


    }
}