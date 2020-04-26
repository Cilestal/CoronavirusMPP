package org.michaellang.common

actual fun runBlocking(block: suspend () -> Unit) {
    kotlinx.coroutines.runBlocking {
        block()
    }
}