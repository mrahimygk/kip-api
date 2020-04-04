package pojo

import java.io.Serializable

data class NoteModel(
    val id: String,
    val userEmail: String,
    val title: String,
    val content: String,
    val color: String,
    val drawingList: List<DrawingModel>,
    val voiceList: List<VoiceModel>,
    val labelList: List<LabelModel>,
    val checkboxList: List<CheckboxModel>,
    val createdDate: String,
    val modifiedDate: String
) : Serializable