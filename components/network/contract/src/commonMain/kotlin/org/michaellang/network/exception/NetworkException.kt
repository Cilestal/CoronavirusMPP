package org.michaellang.network.exception

sealed class NetworkException(message: String?, cause: Throwable?) : Throwable(message, cause) {

    class ConnectionException(message: String? = null, cause: Throwable) :
        NetworkException(message, cause)

    class UnclassifiedException(message: String? = null, cause: Throwable) :
        NetworkException(message, cause)
}