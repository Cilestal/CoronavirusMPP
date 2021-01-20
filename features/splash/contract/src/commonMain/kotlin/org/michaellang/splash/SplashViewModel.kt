package org.michaellang.splash

import org.michaellang.common.base.BaseViewModel
import org.michaellang.livedata.LiveData

interface SplashViewModel : BaseViewModel {
    val startAnimationLiveData: LiveData<Long>

    fun onAnimationEnd()
}