package org.michaellang.common.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class CancelableCoroutineScope(private val dispatcher: CoroutineDispatcher) : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + dispatcher

    fun cancel() {
        job.cancel()
    }

}