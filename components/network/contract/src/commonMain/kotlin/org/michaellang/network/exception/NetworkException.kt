package org.michaellang.network.exception

sealed class NetworkException(message: String?) : Throwable(message) {

    class ConnectionException(message: String? = null) : NetworkException(message)

    class UnclassifiedException(message: String? = null) : NetworkException(message)
}