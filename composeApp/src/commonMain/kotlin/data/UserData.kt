package data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class UserData(val username: String, val password: String /*val dataList: List<String>*/)

object UserDataStore {
    var userData by mutableStateOf((UserData("", "")))
}
