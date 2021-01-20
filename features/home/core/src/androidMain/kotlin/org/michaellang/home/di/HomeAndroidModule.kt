package org.michaellang.home.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.common.extensions.vm
import org.michaellang.common.extensions.vmFactory
import org.michaellang.home.HomeViewModel
import org.michaellang.home.HomeViewModelImpl

class HomeAndroidModule(
    private val activity: AppCompatActivity
) : KodeinModuleHolder {
    override val module = DI.Module("home_android_module") {
        bind<ViewModelProvider.Factory>() with singleton {
            vmFactory {
                HomeViewModelImpl(instance(), instance(), instance())
            }
        }

        bind<HomeViewModel>() with singleton { activity.vm<HomeViewModelImpl>(instance()) }

        bind<FragmentManager>() with singleton {
            activity.supportFragmentManager
        }
    }
}