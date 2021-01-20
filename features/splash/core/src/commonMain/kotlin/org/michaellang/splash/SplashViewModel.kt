package org.michaellang.splash

import kotlinx.coroutines.CoroutineScope
import org.michaellang.common.base.BaseViewModelImpl
import org.michaellang.home.HomeNavigator
import org.michaellang.livedata.MutableLiveData

class SplashViewModelImpl(
    private val homeNavigator: HomeNavigator,
    coroutineScope: CoroutineScope
) : BaseViewModelImpl(coroutineScope), SplashViewModel, CoroutineScope by coroutineScope {

    override val startAnimationLiveData by lazy { MutableLiveData<Long>() }

    override fun onCreate() {
        startAnimationLiveData.setValue(ANIMATION_DURATION)
    }

    override fun onAnimationEnd() {
        homeNavigator.goToHomeScreen()
    }

    override fun clear() {
        startAnimationLiveData.removeObservers()
    }

    companion object {
        private const val ANIMATION_DURATION = 2_000L
    }
}