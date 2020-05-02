package db.dao.note

import db.dao.BaseDao
import org.jetbrains.exposed.sql.ResultRow
import pojo.LabelModel
import java.io.Closeable

interface LabelDao : Closeable, BaseDao<LabelModel> {
    fun insert(labelModel: LabelModel, noteId: String)
    fun batchInsert(labelModel: List<LabelModel>, noteId: String)
    fun update(labelModel: LabelModel)
    fun getAllForNote(noteId: String): List<LabelModel>
}