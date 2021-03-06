package org.michaellang.network

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.michaellang.network.dto.CountryDto
import org.michaellang.network.dto.DayOneDto
import org.michaellang.network.dto.SummaryDto

class NetworkServiceImpl(
    private val networkErrorConverter: NetworkErrorConverter,
    private val httpClient: HttpClient,
    private val baseUrl: String
) : NetworkService {

    override suspend fun getCountryList(): List<CountryDto> {
        return runCatchingQuery {
            httpClient.get {
                withPath("countries")
            }
        }
    }

    override suspend fun getSummary(): SummaryDto {
        return runCatchingQuery {
            httpClient.get {
                withPath("summary")
            }
        }
    }

    override suspend fun getDayOneTotal(country: String): List<DayOneDto> {
        return runCatchingQuery {
            httpClient.get {
                withPath("total/country/$country")
            }
        }
    }

    private fun HttpRequestBuilder.withPath(urlString: String) = url {
        takeFrom(baseUrl)
        encodedPath = urlString
    }

    private suspend inline fun <T> runCatchingQuery(query: () -> T): T {
        return runCatching { query() }
            .onFailure {
                throw networkErrorConverter.mapThrowable(it)
            }
            .getOrThrow()
    }
}