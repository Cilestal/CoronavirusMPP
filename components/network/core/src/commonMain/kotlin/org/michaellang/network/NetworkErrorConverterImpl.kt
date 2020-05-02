package org.michaellang.network

import io.ktor.client.features.ResponseException
import org.michaellang.network.Const.HTTP_FORBIDDEN
import org.michaellang.network.Const.HTTP_UNAUTHORIZED
import org.michaellang.network.exception.NetworkException

class NetworkErrorConverterImpl(
    private val systemErrorConverter: PlatformErrorConverter
) : NetworkErrorConverter {

    override suspend fun mapThrowable(throwable: Throwable): NetworkException {
        return if (throwable is ResponseException) {
            mapHttpException(throwable)
        } else {
            systemErrorConverter.mapThrowable(throwable)
        }
    }

    private fun mapHttpException(httpException: ResponseException): NetworkException {
        return when (httpException.response.status.value) {
            //todo update
            HTTP_UNAUTHORIZED -> NetworkException.UnclassifiedException(cause = httpException)
            HTTP_FORBIDDEN -> NetworkException.UnclassifiedException(cause = httpException)
            else -> NetworkException.UnclassifiedException(cause = httpException)
        }
    }
}