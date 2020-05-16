package org.michaellang.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

actual interface LiveData<T> {
    fun observe(owner: LifecycleOwner, observer: (T) -> Unit)
    fun removeObservers(owner: LifecycleOwner)

    actual fun observe(observer: (T) -> Unit)
    actual fun removeObservers()
}

