package db.dao

import db.entity.Kweets
import pojo.Kweet
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime

class KweetDaoImpl(private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")) : KweetDao {
    override fun top(count: Int) = db.transaction {
        val k2 = Kweets.alias("k2")
        Kweets.join(k2, JoinType.LEFT, Kweets.id, k2[Kweets.replyTo])
            .slice(Kweets.id, k2[Kweets.id].count())
            .selectAll()
            .groupBy(Kweets.id)
            .orderBy(k2[Kweets.id].count(), isAsc = false)
            .limit(count)
            .map { it[Kweets.id] }
    }

    override fun latest(count: Int): List<Int> = db.transaction {
        var attempt = 0
        var allCount: Int? = null

        for (minutes in generateSequence(2) { it * it }) {
            attempt++
            val dt = DateTime.now().minusMinutes(minutes)

            val all = Kweets.slice(Kweets.id)
                .select { Kweets.date.greater(dt) }
                .orderBy(Kweets.date, false)
                .limit(count)
                .map { it[Kweets.id] }

            if (all.size > count) return@transaction all
            if (attempt > 10 && allCount == null) {
                allCount = Kweets.slice(Kweets.id.count()).selectAll().count()
                if (allCount <= count) {
                    return@transaction Kweets.slice(Kweets.id).selectAll().map {
                        it[Kweets.id]
                    }
                }
            }
        }
        emptyList()
    }

    override fun countReplies(id: Int) = db.transaction {
        Kweets.slice(Kweets.id.count()).select {
            Kweets.replyTo.eq(id)
        }.single()[Kweets.id.count()]
    }

    override fun createKweet(user: String, text: String, replyTo: Int?, date: DateTime) = db.transaction {
        Kweets.insert {
            it[Kweets.user] = user
            it[Kweets.date] = date
            it[Kweets.replyTo] = replyTo
            it[Kweets.text] = text
        }.generatedKey ?: throw IllegalStateException("No generated key returned")
    }

    override fun deleteKweet(id: Int) {
        db.transaction {
            Kweets.deleteWhere { Kweets.id.eq(id) }
        }
    }

    override fun getKweet(id: Int) = db.transaction {
        val row = Kweets.select { Kweets.id.eq(id) }.single()
        Kweet(id, row[Kweets.user], row[Kweets.text], row[Kweets.date], row[Kweets.replyTo])

    }

    override fun userKweets(userId: String) = db.transaction {
        Kweets.slice(Kweets.id).select {
            Kweets.user.eq(userId)
        }.orderBy(Kweets.date, false).limit(100).map {
            it[Kweets.id]
        }
    }

    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}