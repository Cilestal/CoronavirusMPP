package org.michaellang.app.di

import androidx.annotation.NavigationRes
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import org.michaellang.app.R
import org.michaellang.app.navigation.HomeNavigatorImpl
import org.michaellang.app.navigation.NewsNavigatorImpl
import org.michaellang.app.navigation.SummaryNavigatorImpl
import org.michaellang.home.Const
import org.michaellang.home.HomeBottomMenuState
import org.michaellang.home.HomeNavigator
import org.michaellang.news.NewsNavigator
import org.michaellang.summary.presentation.SummaryNavigator

class AndroidNavigationModule {
    val module = DI.Module("android_navigation_module") {
        bind<HomeNavigator>() with provider { HomeNavigatorImpl(instance()) }

        bind<SummaryNavigator>() with provider { SummaryNavigatorImpl(instance()) }
        bind<NewsNavigator>() with provider { NewsNavigatorImpl(instance()) }

        bind<@NavigationRes List<Int>>(Const.TAG_HOME_NAV_GRAPH) with singleton {
            HomeBottomMenuState.values()
                .sortedBy { it.index }
                .map(::getHomeBottomNavigation)
        }
    }

    @NavigationRes
    private fun getHomeBottomNavigation(items: HomeBottomMenuState): Int {
        return when (items) {
            HomeBottomMenuState.SUMMARY -> R.navigation.summary_navigation
            HomeBottomMenuState.NEWS -> R.navigation.news_navigation
        }
    }
}