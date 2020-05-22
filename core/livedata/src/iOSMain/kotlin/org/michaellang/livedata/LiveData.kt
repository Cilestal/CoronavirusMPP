package org.michaellang.livedata

actual abstract class LiveData<T : Any> {
    actual abstract fun observe(observer: (T) -> Unit)
    actual abstract fun removeObservers()
    actual abstract fun hasActiveObservers(): Boolean
}