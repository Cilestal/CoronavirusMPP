package org.michaellang.data.model.covid

data class SummaryData(
    val global: GlobalSummaryData,
    val countries: List<CountrySummaryData>,
    val date: Long
)