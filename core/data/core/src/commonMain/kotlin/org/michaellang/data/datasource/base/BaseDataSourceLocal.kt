package org.michaellang.data.datasource.base

import org.michaellang.data.exception.LocalDataSourceException

abstract class BaseDataSourceLocal {
    inline fun <T : Any> runOrThrow(block: () -> T): T {
        return runCatching {
            block()
        }.onFailure {
            throw LocalDataSourceException(it.message, it)
        }.getOrThrow()
    }
}