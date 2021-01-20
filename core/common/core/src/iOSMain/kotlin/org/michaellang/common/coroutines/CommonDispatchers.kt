package org.michaellang.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_queue_t
import kotlin.coroutines.CoroutineContext

actual object CommonDispatchers {
    actual val Main: CoroutineDispatcher = Dispatchers.Main

    actual val Default: CoroutineDispatcher = Dispatchers.Default
    actual val IO: CoroutineDispatcher = Dispatchers.Default

}