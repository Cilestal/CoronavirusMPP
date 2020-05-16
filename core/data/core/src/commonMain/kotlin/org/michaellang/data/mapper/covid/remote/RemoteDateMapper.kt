package org.michaellang.data.mapper.covid.remote

import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.data.mapper.Mapper
import org.michaellang.data.repository.CovidRepository.Companion.DATE_FORMAT
import org.michaellang.network.Const

class RemoteDateMapper(
    private val dateTimeProvider: DateTimeProvider
) : Mapper<String, String> {

    override fun map(data: String): String {
        return dateTimeProvider.format(data, Const.COVID_API_DATE_FORMAT, DATE_FORMAT)
    }
}