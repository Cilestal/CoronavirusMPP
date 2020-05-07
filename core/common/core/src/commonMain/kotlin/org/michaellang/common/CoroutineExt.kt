package org.michaellang.common

expect fun runBlocking(block: suspend () -> Unit)