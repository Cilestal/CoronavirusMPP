package org.michaellang.data.model.statistic

data class DayOneData(
    val country: String,
    val countryCode: String,
    val totalConfirmed: Long,
    val totalDeaths: Long,
    val totalRecovered: Long,
    val date: String
)