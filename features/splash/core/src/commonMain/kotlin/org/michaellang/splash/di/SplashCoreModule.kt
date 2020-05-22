package org.michaellang.splash.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.splash.SplashViewModel
import org.michaellang.splash.SplashViewModelImpl

class SplashCoreModule {
    val module = Kodein.Module("splash_core_module") {
        bind<SplashViewModel>() with singleton {
            SplashViewModelImpl(instance(), instance())
        }
    }
}