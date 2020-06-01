package org.michaellang.ios.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import org.michaellang.home.HomeNavigator
import org.michaellang.ios.navigation.NavigationProvider

class IosNavigationModule(
    private val navigationProvider: NavigationProvider
) {
    val module = Kodein.Module("ios_navigation_module") {
        bind<HomeNavigator>() with singleton {
            navigationProvider.createHomeNavigator()
        }
    }
}