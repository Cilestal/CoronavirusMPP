package org.michaellang.app.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.with
import org.michaellang.app.R
import org.michaellang.app.navigation.HomeNavigatorImpl
import org.michaellang.app.navigation.NewsNavigatorImpl
import org.michaellang.app.navigation.SummaryNavigatorImpl
import org.michaellang.home.Const
import org.michaellang.home.HomeNavigator
import org.michaellang.news.NewsNavigator
import org.michaellang.summary.SummaryNavigator

class AndroidNavigationModule {
    val module = Kodein.Module("android_navigation_module") {
        bind<HomeNavigator>() with provider { HomeNavigatorImpl(instance()) }

        bind<SummaryNavigator>() with provider { SummaryNavigatorImpl(instance()) }

        bind<NewsNavigator>() with provider { NewsNavigatorImpl(instance()) }

        constant(Const.TAG_HOME_NAV_GRAPH) with R.navigation.home_navigation
    }
}