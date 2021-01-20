package org.michaellang.summary.domain.model

class GlobalSummaryDomain(
    val newConfirmed: Long,
    val totalConfirmed: Long,
    val newDeaths: Long,
    val totalDeaths: Long,
    val newRecovered: Long,
    val totalRecovered: Long,
    val date: Long
)