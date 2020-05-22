package org.michaellang.home.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.home.HomeViewModel
import org.michaellang.home.HomeViewModelImpl

class HomeCoreModule : KodeinModuleHolder {
    override val module = Kodein.Module("home_core_module") {
        bind<HomeViewModel>() with singleton {
            HomeViewModelImpl(instance(), instance(), instance())
        }
    }
}