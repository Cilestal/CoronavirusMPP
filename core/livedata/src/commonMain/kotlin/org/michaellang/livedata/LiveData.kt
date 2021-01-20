package org.michaellang.livedata

expect abstract class LiveData<T : Any> {
    abstract fun getValue(): T?
    abstract fun observe(observer: (T) -> Unit)
    abstract fun removeObservers()
    abstract fun hasActiveObservers(): Boolean
}