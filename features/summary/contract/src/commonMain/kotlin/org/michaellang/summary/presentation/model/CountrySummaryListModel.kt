package org.michaellang.summary.presentation.model

sealed class SummaryListModel

data class SummaryListHeader(
    val title: String
) : SummaryListModel()

data class CountrySummaryListModel(
    val country: String,
    val countryFlagUrl: String,
    val totalConfirmed: Long,
    val totalDeaths: Long,
    val totalRecovered: Long
) : SummaryListModel()