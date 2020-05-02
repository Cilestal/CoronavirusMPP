package org.michaellang.data

import org.michaellang.data.model.DataResult

fun <T> T.toDbResult(): DataResult<T> {
    return DataResult(this, DataSource.DATABASE)
}

fun <T> T.toNetworkResult(): DataResult<T> {
    return DataResult(this, DataSource.NETWORK)
}