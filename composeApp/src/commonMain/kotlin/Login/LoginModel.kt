package Login

import Settings.SettingsRepository
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginModel(
    private val settingsRepository: SettingsRepository
) : ScreenModel {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()
    fun setUsername(username: String) {
        _username.value = username
    }

    fun saveUsername() {
        screenModelScope.launch {
            settingsRepository.saveUsername(username.value.trim())
        }
    }

}