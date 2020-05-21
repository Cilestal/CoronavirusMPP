package org.michaellang.livedata

expect class MutableLiveData<T : Any>(
    initValue: T? = null
) : LiveData<T> {
    fun postValue(value: T)

    fun setValue(value: T)
}