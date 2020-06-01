package org.michaellang.summary.presentation.model

data class CountrySummaryListModel(
    val country: String,
    val countryFlagUrl: String,
    val totalConfirmed: Long,
    val totalDeaths: Long,
    val totalRecovered: Long
)