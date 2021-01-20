package org.michaellang.summary.domain.interactor

import org.michaellang.summary.domain.model.CountrySummaryDomain
import org.michaellang.summary.domain.model.GlobalSummaryDomain

interface SummaryInteractor {
    suspend fun getGlobalSummary(): GlobalSummaryDomain
    suspend fun getCountrySummaryData(country: String): CountrySummaryDomain
    suspend fun getCountriesSummaryData(): List<CountrySummaryDomain>
}