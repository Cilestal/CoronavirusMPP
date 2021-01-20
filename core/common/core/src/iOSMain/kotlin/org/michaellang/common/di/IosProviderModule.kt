package org.michaellang.common.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.michaellang.common.provider.IosResourceProviderImpl
import org.michaellang.common.provider.ResourceProvider

class IosProviderModule : KodeinModuleHolder {
    override val module = DI.Module("ios_provider_module") {
        import(ProviderCoreModule().module)
        bind<ResourceProvider>() with singleton {
            IosResourceProviderImpl()
        }
    }
}