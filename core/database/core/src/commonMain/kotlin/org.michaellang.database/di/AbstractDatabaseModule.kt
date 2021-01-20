package org.michaellang.database.di

import com.squareup.sqldelight.db.SqlDriver
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.database.DatabaseSchema.DB_FILE_NAME

abstract class AbstractDatabaseModule : KodeinModuleHolder {
    override val module = DI.Module("core_database_module") {
        import(DatabaseSQLDelightModule().module)
        import(DatabaseDaoModule().module)

        bind<SqlDriver>() with singleton { getSqlDriver(DB_FILE_NAME) }

    }

    abstract fun getSqlDriver(dbFileName: String): SqlDriver
}