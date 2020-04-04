package db.dao

import pojo.NoteModel
import java.io.Closeable

interface LabelDao : Closeable {

    fun init()

    fun get(): List<String>
}