package db.dao

import pojo.LabelModel
import java.io.Closeable

interface LabelDao : Closeable {

    fun init()

    fun getAll(): List<LabelModel>
    fun get(noteId: String): List<LabelModel>
}