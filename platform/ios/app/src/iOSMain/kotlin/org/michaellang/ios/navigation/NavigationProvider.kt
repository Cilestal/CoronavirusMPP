package org.michaellang.ios.navigation

import org.michaellang.home.HomeNavigator

interface NavigationProvider {
    fun createHomeNavigator(): HomeNavigator
}