package pojo

import org.joda.time.DateTime
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
    val isPinned: Boolean,
    val createdDate: DateTime,
    val modifiedDate: DateTime
) : Serializable