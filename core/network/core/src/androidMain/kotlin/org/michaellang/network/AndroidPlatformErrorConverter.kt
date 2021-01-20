package org.michaellang.network

import org.michaellang.network.exception.NetworkException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class AndroidPlatformErrorConverter : PlatformErrorConverter {
    override fun mapThrowable(throwable: Throwable): NetworkException {
        return when {
            throwable is NetworkException                                                    -> throwable
            throwable is UnknownHostException || throwable.cause is UnknownHostException     -> {
                NetworkException.ConnectionException("UnknownHostException", throwable)
            }
            throwable is ConnectException || throwable.cause is ConnectException             -> {
                NetworkException.ConnectionException("ConnectException", throwable)
            }
            throwable is SocketTimeoutException || throwable.cause is SocketTimeoutException -> {
                NetworkException.ConnectionException("SocketTimeoutException", throwable)
            }
            else                                                                             -> NetworkException.UnclassifiedException(
                cause = throwable
            )
        }
    }
}