package org.michaellang.livedata

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.atomic.AtomicBoolean

actual class SingleLiveEvent<T : Any> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: (T) -> Unit) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner) { t ->
            if (pending.compareAndSet(true, false)) {
                observer(t)
            }
        }
    }

    @MainThread
    override fun observe(observer: (T) -> Unit) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe { t ->
            if (pending.compareAndSet(true, false)) {
                observer(t)
            }
        }
    }

    override fun postValue(value: T) {
        pending.set(true)
        super.postValue(value)
    }

    override fun setValue(value: T) {
        pending.set(true)
        super.setValue(value)
    }

    companion object {
        private val TAG = "SingleLiveEvent"
    }
}