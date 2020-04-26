package org.michaellang.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.ios.Ios
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import org.michaellang.network.Const
import org.michaellang.network.IosPlatformErrorConverter
import org.michaellang.network.PlatformErrorConverter
import org.michaellang.network.interceptor.TokenInterceptor
import org.michaellang.network.interceptor.requestInterceptorFeature

class IosNetworkModule(
    private val json: Json
) : AbstractNetworkModule() {

    override fun getPlatformErrorConverter(): PlatformErrorConverter {
        return IosPlatformErrorConverter()
    }

    override fun getHttpClient(tokenInterceptor: TokenInterceptor): HttpClient {
        return HttpClient(Ios) {
            engine {
                configureRequest {
                    setAllowsCellularAccess(true)
                }
            }

            requestInterceptorFeature {
                add(tokenInterceptor)
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    override fun getBaseUrl() = Const.BASE_URL
}