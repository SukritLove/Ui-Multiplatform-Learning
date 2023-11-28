package data

interface UserDataRepo {
    suspend fun addUser(username: String, password: String)
}