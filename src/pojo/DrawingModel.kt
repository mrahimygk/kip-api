package pojo

import java.io.Serializable

data class DrawingModel(
    val id: String,
    val path: String,
    val createdDate: String,
    val modifiedDate: String
) : Serializable