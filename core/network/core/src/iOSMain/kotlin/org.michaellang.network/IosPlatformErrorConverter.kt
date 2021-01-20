package org.michaellang.network

import io.ktor.client.engine.ios.*
import io.ktor.util.*
import org.michaellang.network.exception.NetworkException

class IosPlatformErrorConverter : PlatformErrorConverter {
    @KtorExperimentalAPI
    override fun mapThrowable(throwable: Throwable): NetworkException {
        return when (throwable) {
            is NetworkException -> throwable
            is IosHttpRequestException -> {
                NetworkException.ConnectionException(cause = throwable)
            }
            else                       -> {
                NetworkException.UnclassifiedException(cause = throwable)
            }
        }
    }
}