package db.dao

import pojo.DrawingModel
import java.io.Closeable

interface DrawingDao : Closeable {

    fun init()

    fun getAll(): List<DrawingModel>
    fun get(noteId: String): List<DrawingModel>
}