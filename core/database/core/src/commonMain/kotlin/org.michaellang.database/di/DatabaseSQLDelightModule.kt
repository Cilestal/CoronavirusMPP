package org.michaellang.database.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.database.AppDatabase
import org.michaellang.database.CountryEntityQueries
import org.michaellang.database.CountrySummaryEntityQueries
import org.michaellang.database.GlobalSummaryEntityQueries

internal class DatabaseSQLDelightModule {
    val module = DI.Module("sqldelight_database_module") {
        bind<AppDatabase>() with singleton {
            AppDatabase(instance())
        }

        bind<CountryEntityQueries>() with singleton {
            instance<AppDatabase>().countryEntityQueries
        }

        bind<CountrySummaryEntityQueries>() with singleton {
            instance<AppDatabase>().countrySummaryEntityQueries
        }

        bind<GlobalSummaryEntityQueries>() with singleton {
            instance<AppDatabase>().globalSummaryEntityQueries
        }
    }
}