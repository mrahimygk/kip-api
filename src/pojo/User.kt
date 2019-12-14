package pojo

import org.jetbrains.exposed.sql.Table
import java.io.Serializable

data class User(
    val userID: String,
    val email: String,
    val name: String,
    val hash: String
) : Serializable, Table(){
    
}