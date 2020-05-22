package org.michaellang.livedata

expect open class MutableLiveData<T : Any>(
    initValue: T? = null
) : LiveData<T> {
    open fun postValue(value: T)

    open fun setValue(value: T)
}