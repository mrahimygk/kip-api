package pojo

import java.io.Serializable

data class NoteModel(
    val id: String,
    val userEmail: String,
    val title: String,
    val content: String,
    val color: String,
    val drawingList: String,
    val voiceList: String,
    val labelList: String,
    val checkboxList: String,
    val createdDate: String,
    val modifiedDate: String
) : Serializable