package org.michaellang.network

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import org.michaellang.network.dto.CountryDto
import org.michaellang.network.dto.DayOneDto
import org.michaellang.network.dto.SummaryDto

class NetworkServiceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : NetworkService {

    override suspend fun getCountryList(): List<CountryDto> {
        return httpClient.get {
            withPath("countries")
        }
    }

    override suspend fun getSummary(): SummaryDto {
        return httpClient.get {
            withPath("summary")
        }
    }

    override suspend fun getDayOneTotal(country: String): List<DayOneDto> {
        return httpClient.get {
            withPath("total/country/$country")
        }
    }

    private fun HttpRequestBuilder.withPath(urlString: String) = url {
        takeFrom(baseUrl)
        encodedPath = urlString
    }
}