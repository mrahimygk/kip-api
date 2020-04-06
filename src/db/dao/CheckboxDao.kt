package db.dao

import pojo.CheckboxModel
import java.io.Closeable

interface CheckboxDao : Closeable {

    fun init()

    fun getAll(): List<CheckboxModel>
    fun get(noteId: String): List<CheckboxModel>
    fun insert(checkboxModel: CheckboxModel, noteId: String)
    fun batchInsert(checkboxList: List<CheckboxModel>, noteId: String)
    fun update(checkboxModel: CheckboxModel, noteId: String)
}