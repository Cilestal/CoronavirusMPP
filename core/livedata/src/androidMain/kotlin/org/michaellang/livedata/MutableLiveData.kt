package org.michaellang.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.MutableLiveData as AndroidMutableLiveData

actual open class MutableLiveData<T : Any> actual constructor(
    initValue: T?
) : LiveData<T>() {

    private val observers by lazy {
        mutableListOf<Observer<T>>()
    }

    private val liveData = AndroidMutableLiveData<T>().also { ld ->
        initValue?.let { ld.value = initValue }
    }

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: (T) -> Unit) {
        val ob = Observer(observer)
        observers.add(ob)
        liveData.observe(owner, ob)
    }

    @MainThread
    override fun removeObservers(owner: LifecycleOwner) {
        liveData.removeObservers(owner)
    }

    actual open fun postValue(value: T) {
        liveData.postValue(value)
    }

    actual open fun setValue(value: T) {
        liveData.value = value
    }

    @MainThread
    override fun observe(observer: (T) -> Unit) {
        val ob = Observer(observer)
        observers.add(ob)
        liveData.observeForever(observer)
    }

    @MainThread
    override fun removeObservers() {
        observers.forEach(liveData::removeObserver)
        observers.clear()
    }

    override fun hasActiveObservers(): Boolean {
        return liveData.hasActiveObservers()
    }
}