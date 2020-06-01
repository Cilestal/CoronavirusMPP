package org.michaellang.common.base

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModelImpl(coroutineScope: CoroutineScope) : BaseViewModel {
    override fun clear()
}