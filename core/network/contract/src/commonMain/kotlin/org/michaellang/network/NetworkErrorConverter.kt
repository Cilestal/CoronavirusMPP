package org.michaellang.network

import org.michaellang.network.exception.NetworkException

interface NetworkErrorConverter {
    suspend fun mapThrowable(throwable: Throwable): NetworkException
}