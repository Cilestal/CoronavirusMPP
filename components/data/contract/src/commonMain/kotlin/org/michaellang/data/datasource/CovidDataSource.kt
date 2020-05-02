package org.michaellang.data.datasource

import org.michaellang.data.model.statistic.*

interface CovidDataSource {

    interface Local {
        fun saveCountries(list: List<CountryData>)
        fun getCountry(iso2: String): CountryData?
        fun getCountryList(): List<CountryData>

        fun saveSummaries(list: List<CountrySummaryData>)
        fun getSummary(country: String, date: String): CountrySummaryData?

        fun saveGlobalSummary(data: GlobalSummaryData)
        fun getGlobalSummary(date: String): GlobalSummaryData?

        fun clearCache()
    }

    interface Remote {
        suspend fun getCountryList(): List<CountryData>
        suspend fun getSummary(): SummaryData
        suspend fun getDayOneTotal(country: String): List<DayOneData>
    }

}