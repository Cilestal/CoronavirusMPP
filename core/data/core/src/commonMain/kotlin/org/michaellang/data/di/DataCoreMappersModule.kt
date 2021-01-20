package org.michaellang.data.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.common.annotation.RequiredBindings
import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.data.datasource.remote.mapper.RemoteDataSourceErrorMapper
import org.michaellang.data.mapper.covid.local.LocalCountryDataMapper
import org.michaellang.data.mapper.covid.local.LocalCountryEntityMapper
import org.michaellang.data.mapper.covid.local.LocalCountrySummaryDataMapper
import org.michaellang.data.mapper.covid.local.LocalCountrySummaryEntityMapper
import org.michaellang.data.mapper.covid.local.LocalGlobalSummaryDataMapper
import org.michaellang.data.mapper.covid.local.LocalGlobalSummaryEntityMapper
import org.michaellang.data.mapper.covid.remote.RemoteCountryDataMapper
import org.michaellang.data.mapper.covid.remote.RemoteCountrySummaryDataMapper
import org.michaellang.data.mapper.covid.remote.RemoteDateMapper
import org.michaellang.data.mapper.covid.remote.RemoteDayOneDataMapper
import org.michaellang.data.mapper.covid.remote.RemoteGlobalSummaryDataMapper
import org.michaellang.data.mapper.covid.remote.RemoteSummaryDataMapper

@RequiredBindings(
    DateTimeProvider::class
)
internal class DataCoreMappersModule {
    val module = DI.Module("data_core_mappers_module") {
        import(covidLocalMappersModule)
        import(covidRemoteMappersModule)
    }

    private val covidLocalMappersModule = DI.Module("data_core_local_mappers_module") {
        bind<LocalCountryEntityMapper>() with singleton {
            LocalCountryEntityMapper()
        }
        bind<LocalCountryDataMapper>() with singleton {
            LocalCountryDataMapper()
        }
        bind<LocalCountrySummaryEntityMapper>() with singleton {
            LocalCountrySummaryEntityMapper(instance())
        }
        bind<LocalCountrySummaryDataMapper>() with singleton {
            LocalCountrySummaryDataMapper(instance())
        }
        bind<LocalGlobalSummaryEntityMapper>() with singleton {
            LocalGlobalSummaryEntityMapper(instance())
        }
        bind<LocalGlobalSummaryDataMapper>() with singleton {
            LocalGlobalSummaryDataMapper(instance())
        }
    }

    private val covidRemoteMappersModule = DI.Module("data_core_remote_mappers_module") {
        bind<RemoteDataSourceErrorMapper>() with singleton {
            RemoteDataSourceErrorMapper()
        }
        bind<RemoteDateMapper>() with singleton {
            RemoteDateMapper(instance())
        }
        bind<RemoteCountryDataMapper>() with singleton {
            RemoteCountryDataMapper()
        }
        bind<RemoteSummaryDataMapper>() with singleton {
            RemoteSummaryDataMapper(instance(), instance(), instance())
        }
        bind<RemoteDayOneDataMapper>() with singleton {
            RemoteDayOneDataMapper(instance())
        }
        bind<RemoteCountrySummaryDataMapper>() with singleton {
            RemoteCountrySummaryDataMapper(instance())
        }
        bind<RemoteGlobalSummaryDataMapper>() with singleton {
            RemoteGlobalSummaryDataMapper(instance())
        }
    }
}