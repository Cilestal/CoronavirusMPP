package org.michaellang.database

import com.squareup.sqldelight.db.SqlDriver


object DatabaseSchema : SqlDriver.Schema by AppDatabase.Schema {
    private const val DB_VERSION = 1

    override val version: Int = DB_VERSION
}