package db.dao.note

import db.dao.BaseDao
import org.jetbrains.exposed.sql.ResultRow
import pojo.DrawingModel
import java.io.Closeable

interface DrawingDao : Closeable, BaseDao<DrawingModel> {
    fun insert(drawingModel: DrawingModel, noteId: String)
    fun batchInsert(drawingList: List<DrawingModel>, noteId: String)
    fun update(drawingModel: DrawingModel)
    fun getAllForNote(noteId: String): List<DrawingModel>
}