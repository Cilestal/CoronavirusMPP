package org.michaellang.app

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.michaellang.app.di.AppModule

class App : Application(), KodeinAware {
    override val kodein: Kodein by AppModule(this)

    override fun onCreate() {
        super.onCreate()
        println("")
    }
}