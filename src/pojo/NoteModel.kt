package pojo

import java.io.Serializable

data class NoteModel(
    val id: String,
    val email: String,
    val title: String,
    val content: String,
    val color: String,
    val drawings: List<DrawingModel>,
    val voices: List<VoiceModel>,
    val labels: List<LabelModel>,
    val checkboxes: List<CheckboxModel>,
    val pinned: Boolean,
    val created: String,
    val modified: String
) : Serializable