package org.michaellang.common.di

import android.content.Context
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.michaellang.common.provider.ResourceProvider
import org.michaellang.common.provider.ResourceProviderImpl

class AndroidProviderModule(
    context: Context
) : KodeinModuleHolder {
    override val module = DI.Module("android_provider_module") {
        import(ProviderCoreModule().module)
        bind<ResourceProvider>() with singleton {
            ResourceProviderImpl(context)
        }
    }
}