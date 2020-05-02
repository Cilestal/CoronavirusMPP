package org.michaellang.data.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.datasource.remote.CovidDataSourceRemote
import org.michaellang.network.NetworkService

internal class DataCoreRemoteDatasourceModule(
    networkService: NetworkService
) {
    val module = Kodein.Module("data_core_remote_data_source_module") {
        bind<CovidDataSource.Remote>() with singleton {
            CovidDataSourceRemote(networkService, instance(), instance(), instance(), instance())
        }
    }
}