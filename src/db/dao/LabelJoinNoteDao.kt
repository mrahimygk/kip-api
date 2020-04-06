package db.dao

import pojo.LabelJoinNoteModel
import java.io.Closeable

interface LabelJoinNoteDao : Closeable {

    fun init()

    fun getAll(): List<LabelJoinNoteModel>
    fun get(noteId: String): List<LabelJoinNoteModel>
    fun insert(labelModel: LabelJoinNoteModel, noteId: String)
    fun batchInsert(labelModel: List<LabelJoinNoteModel>, noteId: String)
    fun update(labelModel: LabelJoinNoteModel)
}