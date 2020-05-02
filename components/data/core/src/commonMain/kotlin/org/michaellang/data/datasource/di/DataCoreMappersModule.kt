package org.michaellang.data.datasource.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.data.Const.TAG_TIME_PROVIDER
import org.michaellang.data.datasource.local.mapper.covid.LocalCountryDataMapper
import org.michaellang.data.datasource.local.mapper.covid.LocalCountryEntityMapper
import org.michaellang.data.datasource.local.mapper.covid.LocalCountrySummaryDataMapper
import org.michaellang.data.datasource.local.mapper.covid.LocalCountrySummaryEntityMapper
import org.michaellang.data.datasource.local.mapper.covid.LocalGlobalSummaryDataMapper
import org.michaellang.data.datasource.local.mapper.covid.LocalGlobalSummaryEntityMapper
import org.michaellang.data.datasource.remote.mapper.covid.RemoteCountryDataMapper
import org.michaellang.data.datasource.remote.mapper.covid.RemoteCountrySummaryDataMapper
import org.michaellang.data.datasource.remote.mapper.covid.RemoteDateMapper
import org.michaellang.data.datasource.remote.mapper.covid.RemoteDayOneDataMapper
import org.michaellang.data.datasource.remote.mapper.covid.RemoteGlobalSummaryDataMapper
import org.michaellang.data.datasource.remote.mapper.covid.RemoteSummaryDataMapper

internal class DataCoreMappersModule {
    val module = Kodein.Module("data_core_mappers_module") {
        import(localMappersModule)
        import(remoteMappersModule)
    }

    private val localMappersModule = Kodein.Module("data_core_local_mappers_module") {
        bind<LocalCountryEntityMapper>() with singleton {
            LocalCountryEntityMapper()
        }
        bind<LocalCountryDataMapper>() with singleton {
            LocalCountryDataMapper()
        }
        bind<LocalCountrySummaryEntityMapper>() with singleton {
            LocalCountrySummaryEntityMapper()
        }
        bind<LocalCountrySummaryDataMapper>() with singleton {
            LocalCountrySummaryDataMapper()
        }
        bind<LocalGlobalSummaryEntityMapper>() with singleton {
            LocalGlobalSummaryEntityMapper()
        }
        bind<LocalGlobalSummaryDataMapper>() with singleton {
            LocalGlobalSummaryDataMapper()
        }
    }

    private val remoteMappersModule = Kodein.Module("data_core_remote_mappers_module") {
        bind<RemoteDateMapper>() with singleton {
            RemoteDateMapper(
                instance(TAG_TIME_PROVIDER)
            )
        }
        bind<RemoteCountryDataMapper>() with singleton {
            RemoteCountryDataMapper()
        }
        bind<RemoteSummaryDataMapper>() with singleton {
            RemoteSummaryDataMapper(
                instance(),
                instance(),
                instance()
            )
        }
        bind<RemoteDayOneDataMapper>() with singleton {
            RemoteDayOneDataMapper(
                instance()
            )
        }
        bind<RemoteCountrySummaryDataMapper>() with singleton {
            RemoteCountrySummaryDataMapper(
                instance()
            )
        }
        bind<RemoteGlobalSummaryDataMapper>() with singleton {
            RemoteGlobalSummaryDataMapper(
                instance()
            )
        }
    }
}