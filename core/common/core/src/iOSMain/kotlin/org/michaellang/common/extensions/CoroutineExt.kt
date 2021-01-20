package org.michaellang.common.extensions

actual fun runBlocking(block: suspend () -> Unit) {
    kotlinx.coroutines.runBlocking {
        block()
    }
}