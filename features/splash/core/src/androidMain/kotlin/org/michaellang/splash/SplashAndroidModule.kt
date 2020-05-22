package org.michaellang.splash

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.common.base.BaseActivity
import org.michaellang.splash.di.SplashCoreModule

class SplashAndroidModule {
    val module = Kodein.Module("splash_android_module") {
        import(SplashCoreModule().module)
    }
}