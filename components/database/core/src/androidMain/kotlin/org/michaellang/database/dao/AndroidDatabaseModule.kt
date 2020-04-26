package org.michaellang.database.dao

import com.squareup.sqldelight.db.SqlDriver
import org.michaellang.database.di.DatabaseCoreModule
import com.squareup.sqldelight.android.AndroidSqliteDriver

//class AndroidDatabaseModule(
//    private val context: Context
//) : DatabaseCoreModule() {
//
//    override fun getSqlDriver(): SqlDriver {
//        return AndroidSqliteDriver(LokimoSchema, instance(), "db")
//    }
//
//}