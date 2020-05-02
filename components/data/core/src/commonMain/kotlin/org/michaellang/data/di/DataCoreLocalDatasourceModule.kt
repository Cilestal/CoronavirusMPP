package org.michaellang.data.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.datasource.local.CovidDataSourceLocal
import org.michaellang.database.dao.CountryDao
import org.michaellang.database.dao.CountrySummaryDao
import org.michaellang.database.dao.GlobalSummaryDao

internal class DataCoreLocalDatasourceModule(
    countryDao: CountryDao,
    countrySummaryDao: CountrySummaryDao,
    globalSummaryDao: GlobalSummaryDao
) {
    val module = Kodein.Module("data_core_local_data_source_module") {
        bind<CovidDataSource.Local>() with singleton {
            CovidDataSourceLocal(
                countryDao, countrySummaryDao, globalSummaryDao, instance(),
                instance(), instance(), instance(), instance(), instance()
            )
        }
    }
}