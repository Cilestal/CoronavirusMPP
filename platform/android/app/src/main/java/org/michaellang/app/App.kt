package org.michaellang.app

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.michaellang.app.di.AppModule
import org.michaellang.common.extensions.import

class App : Application(), DIAware {
    override val di: DI by DI.lazy {
        import(AppModule(this@App))
    }

    override fun onCreate() {
        super.onCreate()
        println("")
    }
}