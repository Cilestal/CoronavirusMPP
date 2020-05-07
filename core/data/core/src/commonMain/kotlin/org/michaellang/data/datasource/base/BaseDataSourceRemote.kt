package org.michaellang.data.datasource.base

import org.michaellang.data.datasource.remote.mapper.RemoteDataSourceErrorMapper

abstract class BaseDataSourceRemote(
    protected val remoteDataSourceErrorMapper: RemoteDataSourceErrorMapper
) {

    suspend fun <T : Any> runOrThrow(block: suspend () -> T): T {
        return runCatching {
            block()
        }.onFailure {
            throw remoteDataSourceErrorMapper.map(it)
        }.getOrThrow()
    }
}