package org.michaellang.network

import io.ktor.client.engine.ios.IosHttpRequestException
import io.ktor.util.KtorExperimentalAPI
import org.michaellang.network.exception.NetworkException

class IosPlatformErrorConverter : PlatformErrorConverter {
    @KtorExperimentalAPI
    override fun mapThrowable(throwable: Throwable): NetworkException {
        return when (throwable) {
            is NetworkException -> throwable
            is IosHttpRequestException -> {
                NetworkException.ConnectionException(cause = throwable)
            }
            else -> {
                NetworkException.UnclassifiedException(cause = throwable)
            }
        }
    }
}