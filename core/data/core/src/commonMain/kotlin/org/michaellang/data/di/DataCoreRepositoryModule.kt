package org.michaellang.data.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.common.annotation.RequiredBindings
import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.data.repository.CovidRepository
import org.michaellang.data.repository.CovidRepositoryImpl

@RequiredBindings(
    DateTimeProvider::class
)
internal class DataCoreRepositoryModule {
    val module = DI.Module("data_core_repository_module") {

        bind<CovidRepository>() with singleton {
            CovidRepositoryImpl(instance(), instance(), instance())
        }
    }
}