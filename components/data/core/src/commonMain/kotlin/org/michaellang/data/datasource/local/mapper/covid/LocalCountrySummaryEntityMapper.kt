package org.michaellang.data.datasource.local.mapper.covid

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.statistic.CountrySummaryData
import org.michaellang.database.CountrySummaryEntity

class LocalCountrySummaryEntityMapper : Mapper<CountrySummaryData, CountrySummaryEntity> {
    override fun map(data: CountrySummaryData): CountrySummaryEntity {
        return CountrySummaryEntity.Impl(
            country = data.country,
            date = data.date,
            totalRecovered = data.totalRecovered,
            totalDeaths = data.totalDeaths,
            totalConfirmed = data.totalConfirmed,
            newRecovered = data.newRecovered,
            newDeaths = data.newDeaths,
            newConfirmed = data.newConfirmed,
            countryCode = data.countryCode
        )
    }
}