package org.michaellang.data.model.statistic

data class SummaryData(
    val global: GlobalSummaryData,
    val countries: List<CountrySummaryData>,
    val date: String
)