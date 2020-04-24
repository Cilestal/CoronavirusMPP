package org.michaellang.network

import org.michaellang.network.dto.CountryDto
import org.michaellang.network.dto.DayOneDto
import org.michaellang.network.dto.SummaryDto

interface NetworkService {
    suspend fun getCountryList(): List<CountryDto>
    suspend fun getSummary(): SummaryDto
    suspend fun getDayOneTotal(country: String): List<DayOneDto>
}