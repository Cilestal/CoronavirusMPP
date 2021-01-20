package org.michaellang.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

actual abstract class BaseViewModelImpl actual constructor(
    private val coroutineScope: CoroutineScope
) : ViewModel(), BaseViewModel {

    override fun onCleared() {
        super.onCleared()
        clear()
    }

    actual override fun clear() {
        coroutineScope.cancel()
    }
}