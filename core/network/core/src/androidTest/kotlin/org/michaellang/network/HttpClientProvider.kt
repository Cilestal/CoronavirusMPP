package org.michaellang.network

import android.os.Build
import androidx.annotation.RequiresApi
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import java.nio.file.Files
import java.nio.file.Paths

class HttpClientProvider {

    @RequiresApi(Build.VERSION_CODES.O)
    val client = HttpClient(MockEngine) {
        val json = kotlinx.serialization.json.Json(builderAction = {
            useArrayPolymorphism = true
            ignoreUnknownKeys = true
            isLenient = true
        })

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

                    else             -> error("Unhandled ${request.url}")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getJsonRes(res: String): ByteArray {
        return Files.readAllBytes(Paths.get("$RES_PATH/$res"))
    }

    companion object {
        private const val RES_PATH = "src/androidTest/resources"
    }
}