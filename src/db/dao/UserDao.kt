package db.dao

import org.jetbrains.exposed.sql.statements.InsertStatement
import pojo.User
import java.io.Closeable

interface UserDao : Closeable {

    fun init()

    fun getUser(userId: String, hash: String? = null): User?

    fun getUser(email: String): User?

    fun createUser(user: User): InsertStatement


}