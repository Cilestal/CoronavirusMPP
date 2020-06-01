package org.michaellang.summary.presentation.mapper

import org.michaellang.data.mapper.Mapper
import org.michaellang.summary.domain.model.CountrySummaryDomain
import org.michaellang.summary.presentation.model.CountrySummaryListModel

class CountrySummaryListModelMapper : Mapper<CountrySummaryDomain, CountrySummaryListModel> {

    override fun map(data: CountrySummaryDomain): CountrySummaryListModel {
        return CountrySummaryListModel(
            country = data.country,
            countryFlagUrl = getCountryFlag(data.countryCode),
            totalRecovered = data.totalRecovered,
            totalDeaths = data.totalDeaths,
            totalConfirmed = data.totalConfirmed
        )
    }

    private fun getCountryFlag(countryCode: String): String {
        return "https://www.countryflags.io/$countryCode/flat/64.png"
    }
}