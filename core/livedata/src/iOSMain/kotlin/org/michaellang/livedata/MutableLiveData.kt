package org.michaellang.livedata

//todo check impl
actual class MutableLiveData<T> actual constructor(
    initValue: T?
) : LiveData<T> {

    private var value: T? = initValue
    private val observers = mutableListOf<(T) -> Unit>()

    override fun observe(observer: (T) -> Unit) {
        observers.add(observer)
    }

    override fun removeObservers() {
        observers.clear()
    }

    actual fun postValue(value: T) {
        setValue(value)
        observers.forEach {
            it(value)
        }
    }

    actual fun setValue(value: T) {
        this.value = value
    }

}