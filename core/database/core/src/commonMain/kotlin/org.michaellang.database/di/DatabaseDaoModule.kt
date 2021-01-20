package org.michaellang.database.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.database.dao.CountryDao
import org.michaellang.database.dao.CountryDaoImpl
import org.michaellang.database.dao.CountrySummaryDao
import org.michaellang.database.dao.CountrySummaryDaoImpl
import org.michaellang.database.dao.GlobalSummaryDao
import org.michaellang.database.dao.GlobalSummaryDaoImpl

internal class DatabaseDaoModule {
    val module = DI.Module("database_dao_module") {
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