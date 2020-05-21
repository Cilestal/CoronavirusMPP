package org.michaellang.livedata

//todo check impl
actual class MutableLiveData<T : Any> actual constructor(
    initValue: T?
) : LiveData<T> {

    private var value: T? = initValue
    private val observers = mutableListOf<(T) -> Unit>()

    override fun observe(observer: (T) -> Unit) {
        observers.add(observer)
        value?.let(observer)
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