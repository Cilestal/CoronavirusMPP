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
    actual val Main: CoroutineDispatcher = MainLoopDispatcher

    actual val Default: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())
    actual val IO: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())

    internal class NsQueueDispatcher(
        private val dispatchQueue: dispatch_queue_t
    ) : CoroutineDispatcher() {
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            dispatch_async(dispatchQueue) {
                block.run()
            }
        }
    }

    internal object MainLoopDispatcher : CoroutineDispatcher() {
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            NSRunLoop.mainRunLoop().performBlock {
                block.run()
            }
        }
    }
}