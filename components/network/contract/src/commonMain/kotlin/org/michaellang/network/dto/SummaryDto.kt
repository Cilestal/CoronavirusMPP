package org.michaellang.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SummaryDto(
    @SerialName("Global")
    val global: GlobalSummaryDto,
    @SerialName("Countries")
    val countries: List<CountrySummaryDto>,
    @SerialName("Date")
    val date: String
)