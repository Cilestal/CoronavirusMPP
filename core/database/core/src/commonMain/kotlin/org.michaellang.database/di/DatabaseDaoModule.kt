package org.michaellang.database.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.database.dao.*

internal class DatabaseDaoModule {
    val module = Kodein.Module("database_dao_module") {
        bind<CountryDao>() with singleton {
            CountryDaoImpl(instance())
        }
        bind<CountrySummaryDao>() with singleton {
            CountrySummaryDaoImpl(instance())
        }
        bind<GlobalSummaryDao>() with singleton {
            GlobalSummaryDaoImpl(instance())
        }
    }
}