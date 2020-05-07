package org.michaellang.data.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.common.annotation.RequiredBindings
import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.datasource.remote.CovidDataSourceRemote
import org.michaellang.network.NetworkService

@RequiredBindings(
    NetworkService::class
)
internal class DataCoreRemoteDatasourceModule {
    val module = Kodein.Module("data_core_remote_data_source_module") {
        bind<CovidDataSource.Remote>() with singleton {
            CovidDataSourceRemote(instance(), instance(), instance(), instance(), instance())
        }
    }
}