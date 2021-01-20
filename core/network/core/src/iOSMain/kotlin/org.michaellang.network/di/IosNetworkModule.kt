package org.michaellang.network.di

import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import org.michaellang.common.annotation.RequiredBindings
import org.michaellang.network.Const
import org.michaellang.network.IosPlatformErrorConverter
import org.michaellang.network.PlatformErrorConverter
import org.michaellang.network.interceptor.TokenInterceptor
import org.michaellang.network.interceptor.requestInterceptorFeature

@RequiredBindings(
    Json::class
)
class IosNetworkModule : AbstractNetworkModule() {

    override fun getPlatformErrorConverter(): PlatformErrorConverter {
        return IosPlatformErrorConverter()
    }

    override fun getHttpClient(tokenInterceptor: TokenInterceptor, json: Json): HttpClient {
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