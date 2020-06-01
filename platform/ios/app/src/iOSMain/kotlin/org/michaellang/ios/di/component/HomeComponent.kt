package org.michaellang.ios.di.component

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import org.michaellang.home.HomeViewModel
import org.michaellang.home.HomeViewModelImpl
import org.michaellang.home.di.HomeCoreModule
import org.michaellang.ios.di.IosBaseComponent
import org.michaellang.news.NewsNavigator
import org.michaellang.summary.presentation.SummaryNavigator

class HomeComponent(
    parentKodein: Kodein,
    private val newsNavigator: NewsNavigator,
    private val summaryNavigator: SummaryNavigator
) : IosBaseComponent<HomeViewModel>(parentKodein) {

    override val viewModel by kodein.instance<HomeViewModel>()

    override fun Kodein.MainBuilder.bind() {
        import(HomeCoreModule().module)

        bind<HomeViewModel>() with singleton {
            HomeViewModelImpl(instance(), instance(), instance())
        }

        bind<NewsNavigator>() with provider {
            newsNavigator
        }

        bind<SummaryNavigator>() with provider {
            summaryNavigator
        }
    }
}