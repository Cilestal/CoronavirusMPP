package org.michaellang.data

import org.michaellang.data.model.DataResult

internal fun <T> T.toDbResult(): DataResult<T> {
    return DataResult(this, DataSource.DATABASE)
}

internal fun <T> T.toNetworkResult(): DataResult<T> {
    return DataResult(this, DataSource.NETWORK)
}