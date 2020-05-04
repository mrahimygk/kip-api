package db.dao.note

import db.entity.LabelJoinNoteEntity
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import pojo.LabelJoinNoteModel
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope

class LabelJoinNoteDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
) : LabelJoinNoteDao {
    override fun init() {
        db.transaction {
            create(LabelJoinNoteEntity)
        }
    }

    override fun getAll(): List<LabelJoinNoteModel> = db.transaction {
        LabelJoinNoteEntity.selectAll().map { extractRow(it) }
    }

    override fun get(id: String): LabelJoinNoteModel = db.transaction {
        extractRow(LabelJoinNoteEntity.select { LabelJoinNoteEntity.id.eq(id) }.single())
    }

    override fun getAllForNote(noteId: String): List<LabelJoinNoteModel> = db.transaction {
        LabelJoinNoteEntity.select { LabelJoinNoteEntity.noteid.eq(noteId) }.map { extractRow(it) }
    }

    override fun extractRow(row: ResultRow) = LabelJoinNoteModel(
        row[LabelJoinNoteEntity.id],
        row[LabelJoinNoteEntity.noteid],
        row[LabelJoinNoteEntity.labelid],
        row[LabelJoinNoteEntity.created].toString(),
        row[LabelJoinNoteEntity.modified].toString()
    )

    override fun insert(labelModel: LabelJoinNoteModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun batchInsert(labelModel: List<LabelJoinNoteModel>) {
        TODO("Not yet implemented")
    }

    override fun update(labelModel: LabelJoinNoteModel) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}