package org.michaellang.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CountrySummaryDto(
    @SerialName("Country")
    val country: String,
    @SerialName("CountryCode")
    val countryCode: String,
    @SerialName("Slug")
    val slug: String,
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
    val totalRecovered: Long,
    @SerialName("Date")
    val date: String
)