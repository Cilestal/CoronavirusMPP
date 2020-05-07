package org.michaellang.data.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.common.DateTimeProvider
import org.michaellang.common.annotation.RequiredBindings
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
    val module = Kodein.Module("data_core_mappers_module") {
        import(covidLocalMappersModule)
        import(covidRemoteMappersModule)
    }

    private val covidLocalMappersModule = Kodein.Module("data_core_local_mappers_module") {
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

    private val covidRemoteMappersModule = Kodein.Module("data_core_remote_mappers_module") {
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