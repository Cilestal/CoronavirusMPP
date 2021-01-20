package org.michaellang.data.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.common.annotation.RequiredBindings
import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.datasource.local.CovidDataSourceLocal
import org.michaellang.database.dao.CountryDao
import org.michaellang.database.dao.CountrySummaryDao
import org.michaellang.database.dao.GlobalSummaryDao

@RequiredBindings(
    CountryDao::class,
    CountrySummaryDao::class,
    GlobalSummaryDao::class
)
internal class DataCoreLocalDatasourceModule {
    val module = DI.Module("data_core_local_data_source_module") {
        bind<CovidDataSource.Local>() with singleton {
            CovidDataSourceLocal(
                instance(), instance(), instance(), instance(),
                instance(), instance(), instance(), instance(), instance()
            )
        }
    }
}