package org.michaellang.common.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

actual abstract class BaseViewModelImpl actual constructor(
    private val coroutineScope: CoroutineScope
) : BaseViewModel {

    actual override fun clear() {
        coroutineScope.cancel()
    }
}