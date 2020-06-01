package org.michaellang.data.mapper.covid.local

import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.data.Const.DATE_FORMAT
import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.CountrySummaryData
import org.michaellang.database.CountrySummaryEntity

class LocalCountrySummaryEntityMapper(
    private val localDateTimeProvider: DateTimeProvider
) : Mapper<CountrySummaryData, CountrySummaryEntity> {
    override fun map(data: CountrySummaryData): CountrySummaryEntity {
        return CountrySummaryEntity.Impl(
            country = data.country,
            date = localDateTimeProvider.format(data.date, DATE_FORMAT),
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