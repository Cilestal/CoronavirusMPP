package org.michaellang.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.Json
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.ContentType
import io.ktor.http.headersOf
import kotlinx.serialization.json.JsonConfiguration
import java.nio.file.Files
import java.nio.file.Paths

class HttpClientProvider {
    val client = HttpClient(MockEngine) {
        val json = kotlinx.serialization.json.Json(
            configuration = JsonConfiguration(
                useArrayPolymorphism = true,
                ignoreUnknownKeys = true,
                isLenient = true
            )
        )

        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }

        engine {
            addHandler { request ->
                val responseHeaders =
                    headersOf("Content-Type", ContentType.Application.Json.toString())

                when (request.url.encodedPath) {
                    "countries" -> respond(
                        getJsonRes("CountryListResponse.json"),
                        headers = responseHeaders
                    )
                    "summary" -> respond(
                        getJsonRes("SummaryResponse.json"),
                        headers = responseHeaders
                    )
                    "total/country/" -> respond(
                        getJsonRes("ByCountryResponse.json"),
                        headers = responseHeaders
                    )

                    else -> error("Unhandled ${request.url}")
                }
            }
        }
    }

    private fun getJsonRes(res: String): ByteArray {
        return Files.readAllBytes(Paths.get("$RES_PATH/$res"))
    }

    companion object {
        private const val RES_PATH = "src/jvmTest/resources"
    }
}