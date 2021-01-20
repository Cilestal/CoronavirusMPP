package org.michaellang.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GlobalSummaryDto(
    @SerialName("NewConfirmed")
    val newConfirmed: Long,
    @SerialName("TotalConfirmed")
    val totalConfirmed: Long,
    @SerialName("NewDeaths")
    val newDeaths: Long,
    @SerialName("TotalDeaths")
    val totalDeaths: Long,
    @SerialName("NewRecovered")
    val newRecovered: Long,
    @SerialName("TotalRecovered")
    val totalRecovered: Long
)