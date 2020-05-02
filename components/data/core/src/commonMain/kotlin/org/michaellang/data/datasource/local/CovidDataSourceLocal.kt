package org.michaellang.data.datasource.local

import org.michaellang.data.datasource.CovidDataSource
import org.michaellang.data.datasource.base.BaseDataSourceLocal
import org.michaellang.data.datasource.local.mapper.covid.LocalCountryDataMapper
import org.michaellang.data.datasource.local.mapper.covid.LocalCountryEntityMapper
import org.michaellang.data.datasource.local.mapper.covid.LocalCountrySummaryDataMapper
import org.michaellang.data.datasource.local.mapper.covid.LocalCountrySummaryEntityMapper
import org.michaellang.data.datasource.local.mapper.covid.LocalGlobalSummaryDataMapper
import org.michaellang.data.datasource.local.mapper.covid.LocalGlobalSummaryEntityMapper
import org.michaellang.data.model.statistic.CountryData
import org.michaellang.data.model.statistic.CountrySummaryData
import org.michaellang.data.model.statistic.GlobalSummaryData
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
        list.map(countryEntityMapper::map)
            .let(countryDao::insertCountries)
    }

    override fun getCountry(iso2: String): CountryData? {
        return countryDao.getCountry(iso2)
            ?.let(countryDataMapper::map)
    }

    override fun getCountryList(): List<CountryData> {
        return countryDao.getCountryList()
            .map(countryDataMapper::map)
    }

    override fun saveSummaries(list: List<CountrySummaryData>) {
        list.map(countrySummaryEntityMapper::map)
            .let(countrySummaryDao::insertSummaries)
    }

    override fun getSummary(country: String, date: String): CountrySummaryData? {
        return countrySummaryDao.getSummary(country, date)
            ?.let(countrySummaryDataMapper::map)
    }

    override fun saveGlobalSummary(data: GlobalSummaryData) {
        globalSummaryEntityMapper.map(data)
            .let(globalSummaryDao::insertSummary)
    }

    override fun getGlobalSummary(date: String): GlobalSummaryData? {
        return globalSummaryDao.getSummary(date)
            ?.let(globalSummaryDataMapper::map)
    }

    override fun clearCache() {
        countryDao.clear()
        countrySummaryDao.clear()
        globalSummaryDao.clear()
    }

}