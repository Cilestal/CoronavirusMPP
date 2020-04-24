package org.michaellang.network

import org.michaellang.network.exception.NetworkException

interface PlatformErrorConverter {
    fun mapThrowable(throwable: Throwable): NetworkException
}