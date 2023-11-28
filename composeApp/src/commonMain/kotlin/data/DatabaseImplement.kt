package data

import DriverFactory
import app.cash.sqldelight.db.SqlDriver
import com.example.Database


class UserDataRepoImplement(private val driverFactory: DriverFactory) : UserDataRepo {
    private val driver = driverFactory.createDriver()
    private val database = Database(driver = driver)
    private val userDataQueries = database.userDataQueries

    override suspend fun addUser(username: String, password: String) {
        userDataQueries.insertUser(username,password)
    }
}
