package org.michaellang.common.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider
import org.michaellang.common.provider.DateTimeProvider

class IosProviderModule(
    private val dateTimeProvider: DateTimeProvider
) : KodeinModuleHolder {
    override val module = Kodein.Module("ios_provider_module") {
        bind<DateTimeProvider>() with provider {
            dateTimeProvider
        }
    }
}