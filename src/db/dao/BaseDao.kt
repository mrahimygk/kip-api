package db.dao

import org.jetbrains.exposed.sql.ResultRow

interface BaseDao<T> {
    fun extractRow(row: ResultRow): T
    fun init()
    fun getAll(): List<T>
    fun get(id: String): T

}