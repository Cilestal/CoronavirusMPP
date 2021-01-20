package org.michaellang.common.coroutines

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface SuspendCallback<T> {
    fun onSuccess(result: T)
    fun onFailure(thr: Throwable)
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <T> awaitCallbacks(block: (SuspendCallback<T>) -> Unit): T {
    return suspendCancellableCoroutine { cont: CancellableContinuation<T> ->
        block(
            object : SuspendCallback<T> {
                override fun onSuccess(result: T) = cont.resume(result)
                override fun onFailure(thr: Throwable) = cont.resumeWithException(thr)
            }
        )
    }
}