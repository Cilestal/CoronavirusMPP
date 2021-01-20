package org.michaellang.data.mapper.covid.local

import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.data.Const.DATE_FORMAT
import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.CountrySummaryData
import org.michaellang.database.CountrySummaryEntity

class LocalCountrySummaryDataMapper(
    private val localDateTimeProvider: DateTimeProvider
) : Mapper<CountrySummaryEntity, CountrySummaryData> {
    override fun map(data: CountrySummaryEntity): CountrySummaryData {
        return CountrySummaryData(
            date = localDateTimeProvider.parse(data.date, DATE_FORMAT),
            countryCode = data.countryCode,
            newConfirmed = data.newConfirmed,
            newDeaths = data.newDeaths,
            newRecovered = data.newRecovered,
            totalConfirmed = data.totalConfirmed,
            totalDeaths = data.totalDeaths,
            totalRecovered = data.totalRecovered,
            country = data.country
        )
    }
}