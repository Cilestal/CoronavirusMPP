package org.michaellang.data.di

import org.kodein.di.Kodein
import org.michaellang.common.DateTimeProvider
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.database.dao.CountryDao
import org.michaellang.database.dao.CountrySummaryDao
import org.michaellang.database.dao.GlobalSummaryDao
import org.michaellang.network.NetworkService

class DataCoreModule(
    dateTimeProvider: DateTimeProvider,
    networkService: NetworkService,
    countryDao: CountryDao,
    countrySummaryDao: CountrySummaryDao,
    globalSummaryDao: GlobalSummaryDao
) : KodeinModuleHolder {
    override val module = Kodein.Module("data_core_module") {
        import(DataCoreRepositoryModule(dateTimeProvider).module)
        import(
            DataCoreLocalDatasourceModule(
                countryDao, countrySummaryDao, globalSummaryDao
            ).module
        )
        import(
            DataCoreRemoteDatasourceModule(
                networkService
            ).module
        )
        import(DataCoreMappersModule(dateTimeProvider).module)
    }
}