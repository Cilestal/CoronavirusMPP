package org.michaellang.data.model.statistic

data class CountrySummaryData(
    val country: String,
    val countryCode: String,
    val newConfirmed: Long,
    val totalConfirmed: Long,
    val newDeaths: Long,
    val totalDeaths: Long,
    val newRecovered: Long,
    val totalRecovered: Long,
    val date: String
)