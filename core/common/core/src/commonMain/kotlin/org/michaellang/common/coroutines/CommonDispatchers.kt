package org.michaellang.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher

expect object CommonDispatchers {
    val Main: CoroutineDispatcher
    val Default: CoroutineDispatcher
    val IO: CoroutineDispatcher
}