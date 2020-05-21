package org.michaellang.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.MutableLiveData as AndroidMutableLiveData

actual class MutableLiveData<T : Any> actual constructor(
    initValue: T?
) : LiveData<T> {

    private val observers by lazy {
        mutableListOf<Observer<T>>()
    }

    private val liveData = AndroidMutableLiveData<T>().also { ld ->
        initValue?.let { ld.value = initValue }
    }

    override fun observe(owner: LifecycleOwner, observer: (T) -> Unit) {
        val ob = Observer(observer)
        observers.add(ob)
        liveData.observe(owner, ob)
    }

    override fun removeObservers(owner: LifecycleOwner) {
        liveData.removeObservers(owner)
    }

    actual fun postValue(value: T) {
        liveData.postValue(value)
    }

    actual fun setValue(value: T) {
        liveData.value = value
    }

    override fun observe(observer: (T) -> Unit) {
        val ob = Observer(observer)
        observers.add(ob)
        liveData.observeForever(observer)
    }

    override fun removeObservers() {
        observers.forEach(liveData::removeObserver)
        observers.clear()
    }
}