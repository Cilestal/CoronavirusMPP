package org.michaellang.splash

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.common.extensions.vm
import org.michaellang.common.extensions.vmFactory

class SplashAndroidModule(activity: AppCompatActivity) {
    val module = DI.Module("splash_android_module") {
        bind<SplashViewModel>() with singleton { activity.vm<SplashViewModelImpl>(instance()) }

        bind<ViewModelProvider.Factory>() with singleton {
            vmFactory {
                SplashViewModelImpl(instance(), instance())
            }
        }
    }
}