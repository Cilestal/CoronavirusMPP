package org.michaellang.database.dao

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.michaellang.database.DatabaseSchema
import org.michaellang.database.di.AbstractDatabaseModule

class IosDatabaseModule : AbstractDatabaseModule() {

    override fun getSqlDriver(dbFileName: String): SqlDriver {
        return NativeSqliteDriver(DatabaseSchema, dbFileName)
    }
}