package org.michaellang.ios.di.component

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.home.HomeNavigator
import org.michaellang.splash.SplashViewModel
import org.michaellang.splash.SplashViewModelImpl

class SplashComponent(
    private val homeNavigator: HomeNavigator
) : IosBaseComponent<SplashViewModel>() {

    override val viewModel by kodein.instance<SplashViewModel>()

    override val module = DI.Module("iOS_splash_module") {
        bind<SplashViewModel>() with singleton {
            SplashViewModelImpl(instance(), instance())
        }
        bind<HomeNavigator>() with singleton { homeNavigator }
    }
}