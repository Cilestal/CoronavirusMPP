package org.michaellang.mviproject

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.michaellang.mviproject.di.AppModule

class App : Application(), KodeinAware {
    override val kodein: Kodein by AppModule(this)

}