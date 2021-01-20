package org.michaellang.data.model.covid

data class GlobalSummaryData(
    val newConfirmed: Long,
    val totalConfirmed: Long,
    val newDeaths: Long,
    val totalDeaths: Long,
    val newRecovered: Long,
    val totalRecovered: Long,
    val date: Long
)