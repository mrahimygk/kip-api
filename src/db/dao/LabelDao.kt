package db.dao

import pojo.CheckboxModel
import pojo.LabelModel
import java.io.Closeable

interface LabelDao : Closeable {

    fun init()

    fun getAll(): List<LabelModel>
    fun get(noteId: String): List<LabelModel>
    fun insert(labelModel: LabelModel, noteId: String)
    fun batchInsert(labelModel: List<LabelModel>, noteId: String)
}