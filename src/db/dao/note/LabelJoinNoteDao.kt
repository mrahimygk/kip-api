package db.dao.note

import db.dao.BaseDao
import pojo.LabelJoinNoteModel
import java.io.Closeable

interface LabelJoinNoteDao : Closeable, BaseDao<LabelJoinNoteModel> {
    fun insert(labelModel: LabelJoinNoteModel, noteId: String)
    fun batchInsert(labelModel: List<LabelJoinNoteModel>)
    fun update(labelModel: LabelJoinNoteModel)
    fun getAllForNote(noteId: String): List<LabelJoinNoteModel>
}