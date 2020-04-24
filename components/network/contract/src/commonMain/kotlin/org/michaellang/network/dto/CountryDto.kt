package org.michaellang.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    @SerialName("Country")
    val country: String,
    @SerialName("Slug")
    val slug: String,
    @SerialName("ISO2")
    val iso2: String
)