package db.dao.user

import org.jetbrains.exposed.sql.statements.InsertStatement
import pojo.UserModel
import java.io.Closeable

interface UserDao : Closeable {

    fun init()

    fun getUser(email: String, hash: String? = null): UserModel?
    fun getUserByEmail(email: String): UserModel?

    fun createUser(user: UserModel): InsertStatement

}