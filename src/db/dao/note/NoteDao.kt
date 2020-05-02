package db.dao.note

import db.dao.BaseDao
import pojo.NoteModel
import java.io.Closeable

interface NoteDao : Closeable, BaseDao<NoteModel> {

    fun getPinned(userId: String): List<NoteModel>

    fun getAll(userId: String): List<NoteModel>

    fun insert(noteModel: NoteModel)

    fun delete(noteModel: NoteModel)

    fun update(noteModel: NoteModel)
    /**
     * checks for creation/modification time of the notes
     * and inserts the missing ones, updating others.
     * @returns the number of updates/inserts
     */
    fun sync(notes: List<NoteModel>): Int
}