package org.michaellang.common.extensions

import org.kodein.di.DIAware
import org.kodein.di.instance

inline fun <reified T : Any> DIAware.test(): T {
    val instance: T by instance<T>()
    return instance
}