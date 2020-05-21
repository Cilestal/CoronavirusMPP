package org.michaellang.livedata

actual interface LiveData<T : Any> {
    actual fun observe(observer: (T) -> Unit)
    actual fun removeObservers()
}