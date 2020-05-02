package org.michaellang.data.datasource.remote.mapper.covid

import org.michaellang.data.datasource.remote.mapper.covid.RemoteDateMapper
import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.statistic.DayOneData
import org.michaellang.network.dto.DayOneDto

class RemoteDayOneDataMapper(
    private val dateMapper: RemoteDateMapper
) : Mapper<DayOneDto, DayOneData> {

    override fun map(data: DayOneDto): DayOneData {
        return DayOneData(
            country = data.country,
            countryCode = data.countryCode,
            totalConfirmed = data.confirmed,
            totalDeaths = data.deaths,
            totalRecovered = data.recovered,
            date = dateMapper.map(data.date)
        )
    }
}