package org.michaellang.livedata

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.native.ref.WeakReference

@OptIn(ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
actual open class MutableLiveData<T : Any> actual constructor(
    initValue: T?
) : LiveData<T>() {

    private var ch = BroadcastChannel<T>(CONFLATED)
    private var scope = CoroutineScope(CommonDispatchers.IO)
    private val receivers = mutableListOf<ReceiveChannel<T>>()

    init {
        initValue?.let(::postValue)
    }

    override fun observe(observer: (T) -> Unit) {
        val weakObserver = WeakReference(observer)

        scope.launch {
            ch.openSubscription()
                .also { receivers.add(it) }
                .consumeEach {
                    withContext(CommonDispatchers.Main) {
                        weakObserver.get()?.invoke(it)
                    }
                }
        }
    }

    override fun removeObservers() {
        receivers.forEach { it.cancel() }
    }

    actual open fun postValue(value: T) {
        ch.offer(value)
    }

    actual open fun setValue(value: T) {
        ch.offer(value)
    }

    override fun hasActiveObservers(): Boolean {
        return receivers.isNotEmpty()
    }

}