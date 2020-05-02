package org.michaellang.data.datasource.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.datasource.local.CovidDataSourceLocal
import org.michaellang.data.datasource.remote.CovidDataSourceRemote

internal class DataCoreDatasourceModule {
    val module = Kodein.Module("data_core_data_source_module") {
        import(local)
        import(remote)
    }

    private val local = Kodein.Module("data_core_local_data_source_module") {
        bind<CovidDataSource.Local>() with singleton {
            CovidDataSourceLocal(
                instance(), instance(), instance(), instance(),
                instance(), instance(), instance(), instance(), instance()
            )
        }
    }

    private val remote = Kodein.Module("data_core_remote_data_source_module") {
        bind<CovidDataSource.Remote>() with singleton {
            CovidDataSourceRemote(instance(), instance(), instance(), instance(), instance())
        }
    }
}