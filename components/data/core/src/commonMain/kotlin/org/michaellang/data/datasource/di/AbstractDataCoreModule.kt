package org.michaellang.data.datasource.di

import DateTimeProvider
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.data.Const.TAG_TIME_PROVIDER

abstract class AbstractDataCoreModule : KodeinModuleHolder {
    override val module = Kodein.Module("data_core_module") {
        import(DataCoreRepositoryModule().module)
        import(DataCoreDatasourceModule().module)
        import(DataCoreMappersModule().module)

        import(getNetworkModule().module)
        import(getDatabaseModule().module)

        bind<DateTimeProvider>(TAG_TIME_PROVIDER) with singleton {
            getDateTimeProvider()
        }
    }

    abstract fun getDateTimeProvider(): DateTimeProvider
    abstract fun getNetworkModule(): KodeinModuleHolder
    abstract fun getDatabaseModule(): KodeinModuleHolder
}