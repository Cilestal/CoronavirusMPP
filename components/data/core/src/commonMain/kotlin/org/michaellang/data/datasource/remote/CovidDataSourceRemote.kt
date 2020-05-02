package org.michaellang.data.datasource.remote

import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.datasource.base.BaseDataSourceRemote
import org.michaellang.data.datasource.remote.mapper.covid.RemoteCountryDataMapper
import org.michaellang.data.datasource.remote.mapper.covid.RemoteDayOneDataMapper
import org.michaellang.data.datasource.remote.mapper.covid.RemoteSummaryDataMapper
import org.michaellang.data.model.statistic.CountryData
import org.michaellang.data.model.statistic.DayOneData
import org.michaellang.data.model.statistic.SummaryData
import org.michaellang.network.NetworkService

internal class CovidDataSourceRemote(
    private val networkService: NetworkService,
    private val countryDataMapper: RemoteCountryDataMapper,
    private val summaryDataMapper: RemoteSummaryDataMapper,
    private val dayOneDataMapper: RemoteDayOneDataMapper
) : BaseDataSourceRemote(), CovidDataSource.Remote {

    override suspend fun getCountryList(): List<CountryData> {
        return runOrThrow {
            networkService.getCountryList()
                .map(countryDataMapper::map)
        }
    }

    override suspend fun getSummary(): SummaryData {
        return runOrThrow {
            networkService.getSummary()
                .let(summaryDataMapper::map)
        }
    }

    override suspend fun getDayOneTotal(country: String): List<DayOneData> {
        return runOrThrow {
            networkService.getDayOneTotal(country)
                .map(dayOneDataMapper::map)
        }
    }

}