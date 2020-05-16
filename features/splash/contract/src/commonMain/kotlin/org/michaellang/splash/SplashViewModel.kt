package org.michaellang.splash

import org.michaellang.common.base.BaseViewModel
import org.michaellang.livedata.LiveData

interface SplashContract {
    interface ViewModel : BaseViewModel {
        val startAnimationLiveData: LiveData<Long>

        fun start()
        fun onAnimationEnd()
    }
}