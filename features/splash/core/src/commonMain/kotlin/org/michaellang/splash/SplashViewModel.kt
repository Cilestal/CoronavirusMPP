package org.michaellang.splash

import kotlinx.coroutines.CoroutineScope
import org.michaellang.common.base.BaseViewModelImpl
import org.michaellang.livedata.MutableLiveData

class SplashViewModel(
    coroutineScope: CoroutineScope
) : BaseViewModelImpl(coroutineScope), SplashContract.ViewModel, CoroutineScope by coroutineScope {

    override val startAnimationLiveData by lazy { MutableLiveData<Long>() }

    override fun start() {
        startAnimationLiveData.postValue(ANIMATION_SPEED)
    }

    override fun onAnimationEnd() {
        //todo navigation
    }

    companion object {
        private const val ANIMATION_SPEED = 1_000L
    }
}