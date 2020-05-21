package org.michaellang.livedata

expect interface LiveData<T : Any> {
    fun observe(observer: (T) -> Unit)
    fun removeObservers()
}