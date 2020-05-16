package org.michaellang.data.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.common.annotation.RequiredBindings
import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.data.repository.CovidRepository
import org.michaellang.data.repository.CovidRepositoryImpl

@RequiredBindings(
    DateTimeProvider::class
)
internal class DataCoreRepositoryModule {
    val module = Kodein.Module("data_core_repository_module") {

        bind<CovidRepository>() with singleton {
            CovidRepositoryImpl(instance(), instance(), instance())
        }
    }
}