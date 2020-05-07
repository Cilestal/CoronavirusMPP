package org.michaellang.data.exception

sealed class DataException(message: String?, cause: Throwable?) : Throwable(message, cause)

class LocalDataSourceException(
    message: String?,
    cause: Throwable?
) : DataException(message, cause)

sealed class RemoteDataSourceException(
    message: String?,
    cause: Throwable?
) : DataException(message, cause)

class NetworkConnectionException(message: String? = null, cause: Throwable) :
    RemoteDataSourceException(message, cause)

class NetworkUnclassifiedException(message: String? = null, cause: Throwable) :
    RemoteDataSourceException(message, cause)

class NetworkUnauthorizedException(message: String? = null, cause: Throwable) :
    RemoteDataSourceException(message, cause)

class NetworkForbiddenException(message: String? = null, cause: Throwable) :
    RemoteDataSourceException(message, cause)

class NetworkBadRequestException(message: String? = null, cause: Throwable) :
    RemoteDataSourceException(message, cause)