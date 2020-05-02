package org.michaellang.mviproject.di

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.instance
import org.michaellang.data.di.DataCoreModule
import org.michaellang.mviproject.App
import kotlin.reflect.KProperty

class AppModule(
    app: Application
) : KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(androidXModule(app))


    }

    operator fun getValue(app: App, property: KProperty<*>): Kodein {
        return kodein
    }

}
