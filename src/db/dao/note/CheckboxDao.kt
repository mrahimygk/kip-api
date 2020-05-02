package db.dao.note

import db.dao.BaseDao
import pojo.CheckboxModel
import java.io.Closeable

interface CheckboxDao : Closeable, BaseDao<CheckboxModel> {
    fun insert(checkboxModel: CheckboxModel, noteId: String)
    fun batchInsert(checkboxList: List<CheckboxModel>, noteId: String)
    fun update(checkboxModel: CheckboxModel)
    fun getAllForNote(noteId: String): List<CheckboxModel>
}