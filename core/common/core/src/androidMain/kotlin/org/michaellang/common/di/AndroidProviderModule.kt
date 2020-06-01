package org.michaellang.common.di

import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.common.provider.DateTimeProviderImpl
import org.michaellang.common.provider.ResourceProvider
import org.michaellang.common.provider.ResourceProviderImpl

class AndroidProviderModule(
    context: Context
) : KodeinModuleHolder {
    override val module = Kodein.Module("android_provider_module") {
        bind<DateTimeProvider>() with provider {
            DateTimeProviderImpl()
        }
        bind<ResourceProvider>() with singleton {
            ResourceProviderImpl(context)
        }
    }
}