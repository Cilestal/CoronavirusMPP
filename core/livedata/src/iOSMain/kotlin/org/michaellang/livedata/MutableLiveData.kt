package org.michaellang.livedata

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.native.ref.WeakReference

@OptIn(ExperimentalCoroutinesApi::class)
actual open class MutableLiveData<T : Any> actual constructor(
    initValue: T?
) : LiveData<T>() {

    private var value: T? = null

    private var ch = ConflatedBroadcastChannel<T>()
    private var scope = CoroutineScope(CommonDispatchers.IO)
    private val receivers = mutableListOf<ReceiveChannel<T>>()

    init {
        this.value = initValue
    }

    override fun observe(observer: (T) -> Unit) {
        val weakObserver = WeakReference(observer)

        value?.let(observer::invoke)

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
        this.value = value
        ch.offer(value)
    }

    actual open fun setValue(value: T) {
        this.value = value
        ch.offer(value)
    }

    override fun hasActiveObservers(): Boolean {
        return receivers.isNotEmpty()
    }

    override fun getValue(): T? {
        return value
    }
}