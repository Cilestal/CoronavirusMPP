package org.michaellang.home

import kotlinx.coroutines.CoroutineScope
import org.michaellang.common.base.BaseViewModelImpl
import org.michaellang.livedata.MutableLiveData
import org.michaellang.news.NewsNavigator
import org.michaellang.summary.SummaryNavigator

class HomeViewModelImpl(
    private val summaryNavigator: SummaryNavigator,
    private val newsNavigator: NewsNavigator,
    coroutineScope: CoroutineScope
) : BaseViewModelImpl(coroutineScope), HomeViewModel {

    private var state: HomeBottomMenuState = HomeBottomMenuState.SUMMARY
    override val bottomMenuStateLiveData = MutableLiveData<HomeBottomMenuState>()

    override fun onCreate() {
        updateState(state)
    }

    private fun updateState(state: HomeBottomMenuState) {
        this.state = state
        when (state) {
            HomeBottomMenuState.SUMMARY -> summaryNavigator.goToSummaryScreen()
            HomeBottomMenuState.NEWS -> newsNavigator.goToNewsScreen()
        }
        bottomMenuStateLiveData.setValue(state)
    }

    override fun onSummaryTabClicked() {
        updateState(HomeBottomMenuState.SUMMARY)
    }

    override fun onNewsTabClicked() {
        updateState(HomeBottomMenuState.NEWS)
    }

}