package org.michaellang.livedata

actual interface LiveData<T> {
    actual fun observe(observer: (T) -> Unit)
    actual fun removeObservers()
}