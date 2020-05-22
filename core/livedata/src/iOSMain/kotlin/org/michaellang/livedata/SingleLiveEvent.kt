package org.michaellang.livedata

import kotlin.native.concurrent.AtomicInt

actual class SingleLiveEvent<T : Any> : MutableLiveData<T>() {
    private val pending = AtomicInt(0)

    override fun observe(observer: (T) -> Unit) {
        if (hasActiveObservers()) {
            println("Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe { t ->
            if (pending.compareAndSet(1, 0)) {
                observer(t)
            }
        }
    }

    override fun postValue(value: T) {
        pending.value = 1
        super.postValue(value)
    }

    override fun setValue(value: T) {
        pending.value = 1
        super.setValue(value)
    }
}