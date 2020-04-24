package org.michaellang.network

import org.michaellang.network.exception.NetworkException

interface ErrorConverter {
    suspend fun mapThrowable(throwable: Throwable): NetworkException
}