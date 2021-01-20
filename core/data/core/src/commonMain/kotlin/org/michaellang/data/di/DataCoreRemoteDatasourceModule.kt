package org.michaellang.data.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.common.annotation.RequiredBindings
import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.datasource.remote.CovidDataSourceRemote
import org.michaellang.network.NetworkService

@RequiredBindings(
    NetworkService::class
)
internal class DataCoreRemoteDatasourceModule {
    val module = DI.Module("data_core_remote_data_source_module") {
        bind<CovidDataSource.Remote>() with singleton {
            CovidDataSourceRemote(instance(), instance(), instance(), instance(), instance())
        }
    }
}