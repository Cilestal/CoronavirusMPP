package org.michaellang.ios.di.component

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import org.michaellang.home.HomeViewModel
import org.michaellang.home.HomeViewModelImpl
import org.michaellang.news.NewsNavigator
import org.michaellang.summary.presentation.SummaryNavigator

class HomeComponent(
    private val newsNavigator: NewsNavigator,
    private val summaryNavigator: SummaryNavigator
) : IosBaseComponent<HomeViewModel>() {

    override val viewModel by kodein.instance<HomeViewModel>()

    override val module = DI.Module("iOS_home_module") {
        bind<HomeViewModel>() with singleton {
            HomeViewModelImpl(instance(), instance(), instance())
        }

        bind<NewsNavigator>() with provider { newsNavigator }
        bind<SummaryNavigator>() with provider { summaryNavigator }
    }
}