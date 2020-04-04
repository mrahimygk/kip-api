package db.dao

import pojo.NoteModel
import java.io.Closeable

interface NoteDao : Closeable {

    fun init()

    fun getPinned(user: String): List<Int>

    fun getAll(user: String) : List<Int>

    fun insert(noteModel: NoteModel): Int

    fun delete(noteModel: NoteModel)

    fun update(noteModel: NoteModel)

    fun get(id: String): NoteModel
}