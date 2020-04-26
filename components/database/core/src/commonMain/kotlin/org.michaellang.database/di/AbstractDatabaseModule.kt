package org.michaellang.database.di

import com.squareup.sqldelight.db.SqlDriver
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import org.michaellang.database.DatabaseSchema.DB_FILE_NAME

abstract class AbstractDatabaseModule {
    val module = Kodein.Module("core_database_module") {
        import(DatabaseSQLDelightModule().module)
        import(DatabaseDaoModule().module)

        bind<SqlDriver>() with singleton { getSqlDriver(DB_FILE_NAME) }

    }

    abstract fun getSqlDriver(dbFileName: String): SqlDriver
}