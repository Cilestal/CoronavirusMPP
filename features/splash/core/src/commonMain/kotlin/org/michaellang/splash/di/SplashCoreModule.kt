package org.michaellang.splash.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.splash.SplashContract
import org.michaellang.splash.SplashViewModel

class SplashCoreModule {
    val module = Kodein.Module("splash_core_module") {
        bind<SplashContract.ViewModel>() with singleton {
            SplashViewModel(instance())
        }
    }
}