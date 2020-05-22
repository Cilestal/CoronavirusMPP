package org.michaellang.livedata

import androidx.lifecycle.LifecycleOwner

actual abstract class LiveData<T : Any> {
    abstract fun observe(owner: LifecycleOwner, observer: (T) -> Unit)
    abstract fun removeObservers(owner: LifecycleOwner)

    actual abstract fun observe(observer: (T) -> Unit)
    actual abstract fun removeObservers()
    actual abstract fun hasActiveObservers(): Boolean
}

