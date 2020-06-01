package org.michaellang.summary.domain.mapper

import org.michaellang.data.model.covid.CountrySummaryData
import org.michaellang.summary.domain.model.CountrySummaryDomain

class CountrySummaryDomainMapper {
    fun map(data: CountrySummaryData): CountrySummaryDomain {
        return CountrySummaryDomain(
            country = data.country,
            countryCode = data.countryCode,
            newDeaths = data.newDeaths,
            newRecovered = data.newRecovered,
            totalConfirmed = data.totalConfirmed,
            totalDeaths = data.totalDeaths,
            totalRecovered = data.totalRecovered,
            date = data.date,
            newConfirmed = data.newConfirmed
        )
    }
}