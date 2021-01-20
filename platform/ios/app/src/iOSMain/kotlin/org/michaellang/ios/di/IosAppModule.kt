package org.michaellang.ios.di

import org.kodein.di.DI
import org.michaellang.common.di.IosProviderModule
import org.michaellang.common.di.JsonCoreModule
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.ios.navigation.NavigationProvider

class IosAppModule : KodeinModuleHolder {

    override val module = DI.Module("iOS_app_module") {
        import(JsonCoreModule().module)
        import(IosProviderModule().module)
        import(IosDataModule().module)
    }

}