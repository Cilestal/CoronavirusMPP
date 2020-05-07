package org.michaellang.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.michaellang.database.di.AbstractDatabaseModule

class AndroidDatabaseModule(
    private val context: Context
) : AbstractDatabaseModule() {

    override fun getSqlDriver(dbFileName: String): SqlDriver {
        return AndroidSqliteDriver(DatabaseSchema, context, dbFileName)
    }

}