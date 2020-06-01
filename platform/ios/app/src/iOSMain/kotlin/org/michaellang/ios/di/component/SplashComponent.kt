package org.michaellang.ios.di.component

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.ios.di.IosBaseComponent
import org.michaellang.splash.SplashViewModel
import org.michaellang.splash.SplashViewModelImpl

class SplashComponent(
    parentKodein: Kodein
) : IosBaseComponent<SplashViewModel>(parentKodein) {

    override val viewModel by kodein.instance<SplashViewModel>()

    override fun Kodein.MainBuilder.bind() {
        bind<SplashViewModel>() with singleton {
            SplashViewModelImpl(instance(), instance())
        }
    }
}