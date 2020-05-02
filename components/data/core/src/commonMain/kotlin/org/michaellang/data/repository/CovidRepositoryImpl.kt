package org.michaellang.data.repository

import DateTimeProvider
import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.model.DataResult
import org.michaellang.data.model.statistic.*
import org.michaellang.data.repository.CovidRepository.Companion.DATE_FORMAT
import org.michaellang.data.toDbResult
import org.michaellang.data.toNetworkResult

class CovidRepositoryImpl(
    private val local: CovidDataSource.Local,
    private val remote: CovidDataSource.Remote,
    private val dateProvider: DateTimeProvider
) : CovidRepository {

    override suspend fun getCountryList(): DataResult<List<CountryData>> {
        return runCatching {
            getRemoteCountryList()
        }.onFailure {
            getLocalCountryListOrException(it)
        }.getOrThrow()
    }

    private suspend fun getRemoteCountryList(): DataResult<List<CountryData>> {
        return remote.getCountryList()
            .also(local::saveCountries)
            .toNetworkResult()
    }

    private fun getLocalCountryListOrException(throwable: Throwable): DataResult<List<CountryData>> {
        return local.getCountryList()
            .ifEmpty { throw throwable }
            .toDbResult()
    }

    override suspend fun getGlobalSummary(): DataResult<GlobalSummaryData> {
        local.getGlobalSummary(currentData())?.let {
            return it.toDbResult()
        }

        return loadRemoteSummaryData().global.toNetworkResult()
    }

    override suspend fun getCountrySummaryData(country: String): DataResult<CountrySummaryData> {
        local.getSummary(country, currentData())?.let {
            return it.toDbResult()
        }

        return loadRemoteSummaryData().countries
            .first { it.country == country }
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

    override suspend fun clearCache() {
        local.clearCache()
    }

    private fun currentData() = dateProvider.getCurrentDateTime(DATE_FORMAT)

}