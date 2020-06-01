package org.michaellang.data.mapper.covid.remote

import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.data.mapper.Mapper
import org.michaellang.network.Const

class RemoteDateMapper(
    private val dateTimeProvider: DateTimeProvider
) : Mapper<String, Long> {

    override fun map(data: String): Long {
        return dateTimeProvider.parse(data, Const.COVID_API_DATE_FORMAT)
    }
}