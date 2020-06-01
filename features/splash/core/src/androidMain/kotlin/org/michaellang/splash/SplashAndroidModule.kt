package org.michaellang.splash

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.common.extensions.vmFactory
import org.michaellang.common.extensions.vm

class SplashAndroidModule(activity: AppCompatActivity) {
    val module = Kodein.Module("splash_android_module") {
        bind<SplashViewModel>() with singleton { activity.vm<SplashViewModelImpl>(instance()) }

        bind<ViewModelProvider.Factory>() with singleton {
            vmFactory {
                SplashViewModelImpl(instance(), instance())
            }
        }
    }
}