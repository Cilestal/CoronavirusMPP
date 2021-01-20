package org.michaellang.summary.domain.interactor

import org.michaellang.summary.domain.model.SummaryListSortDomain

interface SummaryListSortInteractor {
    var currentSortType: SummaryListSortDomain

    fun getAvailableSummaryListSort(): List<SummaryListSortDomain>
}