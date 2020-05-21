package org.michaellang.ios.di.splash

import org.kodein.di.Kodein
import org.kodein.di.erased.instance
import org.michaellang.ios.di.IosBaseComponent
import org.michaellang.splash.SplashContract
import org.michaellang.splash.di.SplashCoreModule

class SplashComponent(
    parentKodein: Kodein
) : IosBaseComponent(parentKodein) {

    val viewModel by kodein.instance<SplashContract.ViewModel>()

    override fun Kodein.MainBuilder.bind() {
        import(SplashCoreModule().module)
    }
}