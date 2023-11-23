package Settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun saveUsername(value: String)
    fun getUsername(): Flow<String?>
}