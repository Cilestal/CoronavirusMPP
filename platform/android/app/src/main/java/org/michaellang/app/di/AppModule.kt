package org.michaellang.app.di

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.android.x.androidXModule
import org.michaellang.common.di.AndroidProviderModule
import org.michaellang.common.di.JsonCoreModule
import org.michaellang.common.di.KodeinModuleHolder

class AppModule(app: Application) : KodeinModuleHolder {

    override val module = DI.Module(name = "android_app_module") {
        import(androidXModule(app))
        import(JsonCoreModule().module)
        import(AndroidProviderModule(app).module)
        import(AndroidDataModule(app).module)
        import(AndroidNavigationModule().module)
        import(AndroidGlideModule().module)
    }

}
