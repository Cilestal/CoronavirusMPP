package org.michaellang.summary.domain.interactor

import org.michaellang.data.repository.CovidRepository
import org.michaellang.summary.domain.mapper.CountrySummaryDomainMapper
import org.michaellang.summary.domain.mapper.GlobalSummaryDomainMapper
import org.michaellang.summary.domain.model.CountrySummaryDomain
import org.michaellang.summary.domain.model.GlobalSummaryDomain

class SummaryInteractorImpl(
    private val covidRepository: CovidRepository,
    private val countrySummaryDomainMapper: CountrySummaryDomainMapper,
    private val globalSummaryDomainMapper: GlobalSummaryDomainMapper
) : SummaryInteractor {

    override suspend fun getGlobalSummary(): GlobalSummaryDomain {
        return covidRepository.getGlobalSummary()
            .data.let(globalSummaryDomainMapper::map)
    }

    override suspend fun getCountrySummaryData(country: String): CountrySummaryDomain {
        return covidRepository.getCountrySummaryData(country)
            .data.let(countrySummaryDomainMapper::map)
    }

    override suspend fun getCountriesSummaryData(): List<CountrySummaryDomain> {
        return covidRepository.getCountriesSummaryData()
            .data.map(countrySummaryDomainMapper::map)
    }
}