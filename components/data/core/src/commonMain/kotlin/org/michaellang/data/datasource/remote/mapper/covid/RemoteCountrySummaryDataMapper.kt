package org.michaellang.data.datasource.remote.mapper.covid

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.statistic.CountrySummaryData
import org.michaellang.network.dto.CountrySummaryDto

class RemoteCountrySummaryDataMapper(
    private val remoteDateMapper: RemoteDateMapper
) : Mapper<CountrySummaryDto, CountrySummaryData> {

    override fun map(data: CountrySummaryDto): CountrySummaryData {
        return CountrySummaryData(
            country = data.country,
            countryCode = data.countryCode,
            totalRecovered = data.totalRecovered,
            totalDeaths = data.totalDeaths,
            totalConfirmed = data.totalConfirmed,
            newRecovered = data.newRecovered,
            newDeaths = data.newDeaths,
            newConfirmed = data.newConfirmed,
            date = remoteDateMapper.map(data.date)
        )
    }
}