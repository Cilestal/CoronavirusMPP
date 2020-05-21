package org.michaellang.livedata

import androidx.lifecycle.LifecycleOwner

actual interface LiveData<T : Any> {
    fun observe(owner: LifecycleOwner, observer: (T) -> Unit)
    fun removeObservers(owner: LifecycleOwner)

    actual fun observe(observer: (T) -> Unit)
    actual fun removeObservers()
}

