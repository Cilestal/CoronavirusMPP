package org.michaellang.data.mapper.covid.remote

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.DayOneData
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