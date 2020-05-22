package org.michaellang.home

import org.michaellang.common.base.BaseViewModel
import org.michaellang.livedata.LiveData

interface HomeViewModel : BaseViewModel {
    val bottomMenuStateLiveData: LiveData<HomeBottomMenuState>

    fun onCreate()
    fun onSummaryTabClicked()
    fun onNewsTabClicked()
}