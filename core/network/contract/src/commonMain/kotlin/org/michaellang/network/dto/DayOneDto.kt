package org.michaellang.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayOneDto(
    @SerialName("Country")
    val country: String,
    @SerialName("CountryCode")
    val countryCode: String,
    @SerialName("Province")
    val province: String,
    @SerialName("City")
    val city: String,
    @SerialName("CityCode")
    val cityCode: String,
    @SerialName("Lat")
    val lat: String,
    @SerialName("Lon")
    val lon: String,
    @SerialName("Confirmed")
    val confirmed: Long,
    @SerialName("Deaths")
    val deaths: Long,
    @SerialName("Recovered")
    val recovered: Long,
    @SerialName("Active")
    val active: Long,
    @SerialName("Date")
    val date: String
)
