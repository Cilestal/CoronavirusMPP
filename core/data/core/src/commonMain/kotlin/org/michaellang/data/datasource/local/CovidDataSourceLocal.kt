package org.michaellang.data.datasource.local

import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.datasource.base.BaseDataSourceLocal
import org.michaellang.data.mapper.covid.local.LocalCountryDataMapper
import org.michaellang.data.mapper.covid.local.LocalCountryEntityMapper
import org.michaellang.data.mapper.covid.local.LocalCountrySummaryDataMapper
import org.michaellang.data.mapper.covid.local.LocalCountrySummaryEntityMapper
import org.michaellang.data.mapper.covid.local.LocalGlobalSummaryDataMapper
import org.michaellang.data.mapper.covid.local.LocalGlobalSummaryEntityMapper
import org.michaellang.data.model.covid.CountryData
import org.michaellang.data.model.covid.CountrySummaryData
import org.michaellang.data.model.covid.GlobalSummaryData
import org.michaellang.database.dao.CountryDao
import org.michaellang.database.dao.CountrySummaryDao
import org.michaellang.database.dao.GlobalSummaryDao

internal class CovidDataSourceLocal(
    private val countryDao: CountryDao,
    private val countrySummaryDao: CountrySummaryDao,
    private val globalSummaryDao: GlobalSummaryDao,
    private val countryEntityMapper: LocalCountryEntityMapper,
    private val countryDataMapper: LocalCountryDataMapper,
    private val countrySummaryEntityMapper: LocalCountrySummaryEntityMapper,
    private val countrySummaryDataMapper: LocalCountrySummaryDataMapper,
    private val globalSummaryEntityMapper: LocalGlobalSummaryEntityMapper,
    private val globalSummaryDataMapper: LocalGlobalSummaryDataMapper
) : BaseDataSourceLocal(), CovidDataSource.Local {

    override fun saveCountries(list: List<CountryData>) {
        runOrThrow {
            list.map(countryEntityMapper::map)
                .let(countryDao::insertCountries)
        }
    }

    override fun getCountry(iso2: String): CountryData? {
        runOrThrow {
            return countryDao.getCountry(iso2)
                ?.let(countryDataMapper::map)
        }
    }

    override fun getCountryList(): List<CountryData> {
        runOrThrow {
            return countryDao.getCountryList()
                .map(countryDataMapper::map)
        }
    }

    override fun saveSummaries(list: List<CountrySummaryData>) {
        runOrThrow {
            list.map(countrySummaryEntityMapper::map)
                .let(countrySummaryDao::insertSummaries)
        }
    }

    override fun getSummary(country: String, date: String): CountrySummaryData? {
        runOrThrow {
            return countrySummaryDao.getSummary(country, date)
                ?.let(countrySummaryDataMapper::map)
        }
    }

    override fun getCountriesSummary(date: String): List<CountrySummaryData> {
        runOrThrow {
            return countrySummaryDao.countrySummaries(date)
                .map(countrySummaryDataMapper::map)
        }
    }

    override fun saveGlobalSummary(data: GlobalSummaryData) {
        runOrThrow {
            globalSummaryEntityMapper.map(data)
                .let(globalSummaryDao::insertSummary)
        }
    }

    override fun getGlobalSummary(date: String): GlobalSummaryData? {
        runOrThrow {
            return globalSummaryDao.getSummary(date)
                ?.let(globalSummaryDataMapper::map)
        }
    }

    override fun clearCache() {
        //todo impl
        runOrThrow {
            countryDao.clear()
            countrySummaryDao.clear()
            globalSummaryDao.clear()
        }
    }

}