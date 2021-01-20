package org.michaellang.data.mapper

interface Mapper<T, V> {
    fun map(data: T): V
}