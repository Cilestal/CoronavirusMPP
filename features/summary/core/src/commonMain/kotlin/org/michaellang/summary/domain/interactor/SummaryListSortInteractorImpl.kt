package org.michaellang.summary.domain.interactor

import org.michaellang.data.repository.CovidRepository
import org.michaellang.summary.domain.model.SummaryListSortDomain

class SummaryListSortInteractorImpl(
    private val covidRepository: CovidRepository
) : SummaryListSortInteractor {

    override var currentSortType: SummaryListSortDomain = SummaryListSortDomain.TOTAL_CONFIRMED

    override fun getAvailableSummaryListSort() = availableSortList

    companion object {
        private val availableSortList = listOf(
            SummaryListSortDomain.TOTAL_CONFIRMED,
            SummaryListSortDomain.TOTAL_DEATHS,
            SummaryListSortDomain.TOTAL_RECOVERED
        )
    }
}