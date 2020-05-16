package org.michaellang.livedata

expect interface LiveData<T> {
    fun observe(observer: (T) -> Unit)
    fun removeObservers()
}