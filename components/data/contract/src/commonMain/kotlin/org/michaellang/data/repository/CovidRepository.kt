package org.michaellang.data.repository

import org.michaellang.data.model.DataResult
import org.michaellang.data.model.statistic.*

interface CovidRepository {
    suspend fun getCountryList(): DataResult<List<CountryData>>
    suspend fun getGlobalSummary(): DataResult<GlobalSummaryData>
    suspend fun getCountrySummaryData(country: String): DataResult<CountrySummaryData>
    suspend fun getDayOneData(country: String): DataResult<List<DayOneData>>

    suspend fun clearCache()

    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd"
    }
}