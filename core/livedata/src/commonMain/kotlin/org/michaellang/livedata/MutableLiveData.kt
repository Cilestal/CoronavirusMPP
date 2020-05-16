package org.michaellang.livedata

expect class MutableLiveData<T>(
    initValue: T? = null
) : LiveData<T> {
    fun postValue(value: T)

    fun setValue(value: T)
}