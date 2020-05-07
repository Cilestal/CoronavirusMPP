package org.michaellang.data.model

import org.michaellang.data.DataSource

data class DataResult<T>(
    val data: T,
    val source: DataSource
)