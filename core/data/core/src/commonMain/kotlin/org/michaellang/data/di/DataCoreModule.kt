package org.michaellang.data.di

import org.kodein.di.DI
import org.michaellang.common.annotation.RequiredBindings
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.database.dao.CountryDao
import org.michaellang.database.dao.CountrySummaryDao
import org.michaellang.database.dao.GlobalSummaryDao
import org.michaellang.network.NetworkService

@RequiredBindings(
    DateTimeProvider::class,
    NetworkService::class,
    CountryDao::class,
    CountrySummaryDao::class,
    GlobalSummaryDao::class,
    NetworkService::class
)
class DataCoreModule : KodeinModuleHolder {
    override val module = DI.Module("data_core_module") {
        import(DataCoreRepositoryModule().module)
        import(DataCoreLocalDatasourceModule().module)
        import(DataCoreRemoteDatasourceModule().module)
        import(DataCoreMappersModule().module)
    }
}