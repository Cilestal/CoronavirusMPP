package org.michaellang.mviproject.di

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.michaellang.common.di.AndroidProviderModule
import org.michaellang.common.di.JsonModule
import org.michaellang.mviproject.App
import kotlin.reflect.KProperty

class AppModule(
    app: Application
) : KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(androidXModule(app))
        import(JsonModule().module)
        import(AndroidProviderModule().module)
        import(AndroidDataModule(app).module)
    }

    operator fun getValue(app: App, property: KProperty<*>): Kodein {
        return kodein
    }

}
