package db.dao

import pojo.CheckboxModel
import pojo.DrawingModel
import java.io.Closeable

interface DrawingDao : Closeable {

    fun init()

    fun getAll(): List<DrawingModel>
    fun get(noteId: String): List<DrawingModel>
    fun insert(drawingModel: DrawingModel, noteId: String)
    fun batchInsert(drawingModel: List<DrawingModel>, noteId: String)
}