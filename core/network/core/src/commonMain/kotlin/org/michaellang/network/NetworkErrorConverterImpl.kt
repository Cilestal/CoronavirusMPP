package org.michaellang.network

import io.ktor.client.features.ResponseException
import org.michaellang.network.Const.HTTP_BAD_REQUEST
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
            HTTP_UNAUTHORIZED -> NetworkException.UnauthorizedException(
                httpException.message,
                cause = httpException
            )
            HTTP_FORBIDDEN -> NetworkException.ForbiddenException(
                httpException.message,
                cause = httpException
            )
            HTTP_BAD_REQUEST -> NetworkException.BadRequestException(
                httpException.message,
                cause = httpException
            )
            else -> NetworkException.UnclassifiedException(
                httpException.message,
                cause = httpException
            )
        }
    }
}