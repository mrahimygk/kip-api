package db.dao

import pojo.LabelJoinNoteModel
import java.io.Closeable

interface LabelJoinNoteDao : Closeable {

    fun init()

    fun getAll(): List<LabelJoinNoteModel>
    fun get(ljnId: String): LabelJoinNoteModel
    fun getAllForNote(noteId: String): List<LabelJoinNoteModel>
    fun insert(labelModel: LabelJoinNoteModel, noteId: String)
    fun batchInsert(labelModel: List<LabelJoinNoteModel>)
    fun update(labelModel: LabelJoinNoteModel)
}