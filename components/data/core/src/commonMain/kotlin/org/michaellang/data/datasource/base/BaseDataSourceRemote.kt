package org.michaellang.data.datasource.base

abstract class BaseDataSourceRemote {

    suspend fun <T : Any> runOrThrow(block: suspend () -> T): T {
        return runCatching {
            block()
        }.onFailure {
            //throw networkErrorConverter.mapThrowable(it)
            throw it
        }.getOrThrow()
    }
}