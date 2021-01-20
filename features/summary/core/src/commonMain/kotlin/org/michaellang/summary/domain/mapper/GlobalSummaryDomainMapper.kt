package org.michaellang.summary.domain.mapper

import org.michaellang.data.model.covid.GlobalSummaryData
import org.michaellang.summary.domain.model.GlobalSummaryDomain

class GlobalSummaryDomainMapper {
    fun map(data: GlobalSummaryData): GlobalSummaryDomain {
        return GlobalSummaryDomain(
            newConfirmed = data.newConfirmed,
            date = data.date,
            totalRecovered = data.totalRecovered,
            totalDeaths = data.totalDeaths,
            totalConfirmed = data.totalConfirmed,
            newRecovered = data.newRecovered,
            newDeaths = data.newDeaths
        )
    }
}