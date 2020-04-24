package org.michaellang.network

import org.michaellang.network.exception.NetworkException

class IosPlatformErrorConverter : PlatformErrorConverter {
    override fun mapThrowable(throwable: Throwable): NetworkException {
        return TODO()
    }

}