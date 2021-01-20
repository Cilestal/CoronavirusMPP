package org.michaellang.summary.presentation.model

import org.michaellang.summary.domain.model.SummaryListSortDomain

data class SummaryListSortModel(
    val title: String,
    val selected: Boolean = false,
    val sort: SummaryListSortDomain
)