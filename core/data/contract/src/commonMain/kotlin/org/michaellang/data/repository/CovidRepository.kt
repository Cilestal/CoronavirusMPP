package org.michaellang.data.repository

import org.michaellang.data.model.DataResult
import org.michaellang.data.model.covid.CountryData
import org.michaellang.data.model.covid.CountrySummaryData
import org.michaellang.data.model.covid.DayOneData
import org.michaellang.data.model.covid.GlobalSummaryData

interface CovidRepository {
    suspend fun getCountryList(): DataResult<List<CountryData>>
    suspend fun getGlobalSummary(): DataResult<GlobalSummaryData>
    suspend fun getCountriesSummaryData(): DataResult<List<CountrySummaryData>>
    suspend fun getCountrySummaryData(country: String): DataResult<CountrySummaryData>
    suspend fun getDayOneData(country: String): DataResult<List<DayOneData>>

    suspend fun clearCache()
}