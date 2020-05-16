package org.michaellang.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

actual open class BaseViewModelImpl actual constructor(
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}