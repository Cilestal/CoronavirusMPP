package org.michaellang.data.datasource.remote.mapper

import org.michaellang.data.exception.NetworkBadRequestException
import org.michaellang.data.exception.NetworkConnectionException
import org.michaellang.data.exception.NetworkForbiddenException
import org.michaellang.data.exception.NetworkUnauthorizedException
import org.michaellang.data.exception.NetworkUnclassifiedException
import org.michaellang.data.exception.RemoteDataSourceException
import org.michaellang.network.exception.NetworkException

class RemoteDataSourceErrorMapper {
    fun map(throwable: Throwable): RemoteDataSourceException {
        return when (throwable) {
            is NetworkException.ConnectionException -> NetworkConnectionException(
                throwable.message,
                throwable
            )
            is NetworkException.UnclassifiedException -> NetworkUnclassifiedException(
                throwable.message,
                throwable
            )
            is NetworkException.UnauthorizedException -> NetworkUnauthorizedException(
                throwable.message,
                throwable
            )
            is NetworkException.ForbiddenException -> NetworkForbiddenException(
                throwable.message,
                throwable
            )
            is NetworkException.BadRequestException -> NetworkBadRequestException(
                throwable.message,
                throwable
            )
            else -> NetworkUnclassifiedException(throwable.message, throwable)
        }
    }
}