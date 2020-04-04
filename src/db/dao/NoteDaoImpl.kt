package db.dao

import db.entity.*
import org.jetbrains.exposed.sql.*

class NoteDaoImpl(
    private val labelDao: LabelDao,
    private val drawingDao: DrawingDao,
    private val voiceDao: VoiceDao,
    private val checkboxDao: CheckboxDao,
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
) : NoteDao {

    override fun init() {
        db.transaction {
            create(NoteEntity)
        }
    }

    override fun get(id: String) = db.transaction {
        val row = NoteEntity.select { NoteEntity.id.eq(id) }.single()
        val labels = labelDao.get(id)
        val drawings = drawingDao.get(id)
        val voices = voiceDao.get(id)
        val checkBoxes = checkboxDao.get(id)

        pojo.NoteModel(
            row[NoteEntity.id],
            row[NoteEntity.userEmail],
            row[NoteEntity.title],
            row[NoteEntity.content],
            row[NoteEntity.color],
            drawings,
            voices,
            labels,
            checkBoxes,
            row[NoteEntity.isPinned],
            row[NoteEntity.createdDate],
            row[NoteEntity.modifiedDate]
        )

    }

    override fun delete(noteModel: pojo.NoteModel) {
        db.transaction {
            NoteEntity.deleteWhere { NoteEntity.id.eq(noteModel.id) }
        }
    }

    override fun getAll(userId: String) = db.transaction {
        val tuples = NoteEntity.select { NoteEntity.userEmail.eq(userId) }
        tuples.map { row ->
            get(row[NoteEntity.id])
        }
    }

    override fun insert(noteModel: pojo.NoteModel) {
        db.transaction {
            NoteEntity.insert {
                it[id] = noteModel.id
                it[userEmail] = noteModel.userEmail
                it[title] = noteModel.title
                it[content] = noteModel.content
                it[color] = noteModel.color
                it[createdDate] = noteModel.createdDate
                it[modifiedDate] = noteModel.modifiedDate
            }

            if (noteModel.drawingList.isNotEmpty()) {
                drawingDao.batchInsert(noteModel.drawingList, noteModel.id)
            }

            //TODO: move these to according dao
            if (noteModel.checkboxList.isNotEmpty()) {
                CheckboxEntity.batchInsert(noteModel.checkboxList) { checkbox ->
                    this[CheckboxEntity.id] = checkbox.id
                    this[CheckboxEntity.noteId] = noteModel.id
                    this[CheckboxEntity.text] = checkbox.text
                    this[CheckboxEntity.indent] = checkbox.indent
                    this[CheckboxEntity.checked] = checkbox.checked
                    this[CheckboxEntity.createdDate] = checkbox.createdDate
                    this[CheckboxEntity.modifiedDate] = checkbox.modifiedDate
                }
            }

            if (noteModel.labelList.isNotEmpty()) {
                LabelEntity.batchInsert(noteModel.labelList) { label ->
                    this[LabelEntity.id] = label.id
                    this[LabelEntity.text] = label.text
                    this[LabelEntity.createdDate] = label.createdDate
                    this[LabelEntity.modifiedDate] = label.modifiedDate
                }

                LabelJoinNoteEntity.batchInsert(noteModel.labelList) { label ->
                    this[LabelJoinNoteEntity.id] = "${noteModel.id}${label.id}"
                    this[LabelJoinNoteEntity.labelId] = label.id
                    this[LabelJoinNoteEntity.noteId] = noteModel.id
                    this[LabelJoinNoteEntity.createdDate] = label.createdDate
                    this[LabelJoinNoteEntity.modifiedDate] = label.modifiedDate
                }
            }

            if (noteModel.voiceList.isNotEmpty()) {
                VoiceEntity.batchInsert(noteModel.voiceList) { voice ->
                    this[VoiceEntity.id] = voice.id
                    this[VoiceEntity.noteId] = noteModel.id
                    this[VoiceEntity.path] = voice.path
                    this[VoiceEntity.createdDate] = voice.createdDate
                    this[VoiceEntity.modifiedDate] = voice.modifiedDate
                }
            }
        }
    }

    override fun getPinned(userId: String): List<pojo.NoteModel> {
        return getAll(userId).filter { it.isPinned }
    }

    override fun update(noteModel: pojo.NoteModel) {
        TODO("Not yet implemented")
    }

//    override fun top(count: Int) = db.transaction {
//        TODO()
////        val k2 = NoteModel.alias("k2")
////        NoteModel.join(k2, JoinType.LEFT, NoteModel.id, k2[NoteModel.replyTo])
////            .slice(NoteModel.id, k2[NoteModel.id].count())
////            .selectAll()
////            .groupBy(NoteModel.id)
////            .orderBy(k2[NoteModel.id].count(), isAsc = false)
////            .limit(count)
////            .map { it[NoteModel.id] }
//    }

//    override fun latest(count: Int): List<Int> = db.transaction {
//        TODO()
////        var attempt = 0
////        var allCount: Int? = null
////
////        for (minutes in generateSequence(2) { it * it }) {
////            attempt++
////            val dt = DateTime.now().minusMinutes(minutes)
////
////            val all = NoteModel.slice(NoteModel.id)
////                .select { NoteModel.date.greater(dt) }
////                .orderBy(NoteModel.date, false)
////                .limit(count)
////                .map { it[NoteModel.id] }
////
////            if (all.size > count) return@transaction all
////            if (attempt > 10 && allCount == null) {
////                allCount = NoteModel.slice(NoteModel.id.count()).selectAll().count()
////                if (allCount <= count) {
////                    return@transaction NoteModel.slice(NoteModel.id).selectAll().map {
////                        it[NoteModel.id]
////                    }
////                }
////            }
////        }
////        emptyList()
//    }

//    override fun countReplies(id: Int) = db.transaction {
//        TODO()
////        NoteModel.slice(NoteModel.id.count()).select {
////            NoteModel.replyTo.eq(id)
////        }.single()[NoteModel.id.count()]
//    }

//    override fun createKweet(user: String, text: String, replyTo: Int?, date: DateTime) = db.transaction {
//        TODO()
//        NoteModel.insert {
//            it[NoteModel.user] = user
//            it[NoteModel.date] = date
//            it[NoteModel.replyTo] = replyTo
//            it[NoteModel.text] = text
//        }.generatedKey ?: throw IllegalStateException("No generated key returned")
//    }


//    override fun userKweets(userId: String) = db.transaction {
//        TODO()
////        NoteModel.slice(NoteModel.id).select {
////            NoteModel.user.eq(userId)
////        }.orderBy(NoteModel.date, false).limit(100).map {
////            it[NoteModel.id]
////        }
//    }

    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}