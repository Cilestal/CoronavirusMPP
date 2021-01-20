package org.michaellang.data.repository

import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.data.Const.DATE_FORMAT
import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.model.DataResult
import org.michaellang.data.model.covid.CountryData
import org.michaellang.data.model.covid.CountrySummaryData
import org.michaellang.data.model.covid.DayOneData
import org.michaellang.data.model.covid.GlobalSummaryData
import org.michaellang.data.model.covid.SummaryData
import org.michaellang.data.toDbResult
import org.michaellang.data.toNetworkResult

class CovidRepositoryImpl(
    private val local: CovidDataSource.Local,
    private val remote: CovidDataSource.Remote,
    private val dateProvider: DateTimeProvider
) : CovidRepository {

    private val currentDate = dateProvider.getCurrentDateTime(DATE_FORMAT)

    override suspend fun getCountryList(): DataResult<List<CountryData>> {
        getLocalCountryList()?.let {
            return it
        }

        return getRemoteCountryList()
    }

    private suspend fun getRemoteCountryList(): DataResult<List<CountryData>> {
        return remote.getCountryList()
            .also(local::saveCountries)
            .toNetworkResult()
    }

    private fun getLocalCountryList(): DataResult<List<CountryData>>? {
        return local.getCountryList()
            .takeIf { it.isNotEmpty() }
            ?.toDbResult()
    }

    override suspend fun getGlobalSummary(): DataResult<GlobalSummaryData> {
        local.getGlobalSummary(currentDate)?.let {
            return it.toDbResult()
        }

        return loadRemoteSummaryData().global.toNetworkResult()
    }

    override suspend fun getCountrySummaryData(country: String): DataResult<CountrySummaryData> {
        local.getSummary(country, currentDate)?.let {
            return it.toDbResult()
        }

        return loadRemoteSummaryData().countries
            .first { it.country.equals(country, true) }
            .toNetworkResult()
    }

    override suspend fun getDayOneData(country: String): DataResult<List<DayOneData>> {
        return remote.getDayOneTotal(country)
            .toNetworkResult()
    }

    private suspend fun loadRemoteSummaryData(): SummaryData {
        val summary = remote.getSummary()

        local.saveGlobalSummary(summary.global)
        local.saveSummaries(summary.countries)

        return summary
    }

    override suspend fun getCountriesSummaryData(): DataResult<List<CountrySummaryData>> {
        local.getCountriesSummary(currentDate)
            .takeIf { it.isNotEmpty() }
            ?.let {
                return it.toDbResult()
            }

        return loadRemoteSummaryData().countries
            .toNetworkResult()
    }

    override suspend fun clearCache() {
        local.clearCache()
    }

}