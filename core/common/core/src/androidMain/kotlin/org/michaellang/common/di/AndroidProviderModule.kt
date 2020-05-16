package org.michaellang.common.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider
import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.common.provider.DateTimeProviderImpl

class AndroidProviderModule : KodeinModuleHolder {
    override val module = Kodein.Module("android_provider_module") {
        bind<DateTimeProvider>() with provider {
            DateTimeProviderImpl()
        }
    }
}