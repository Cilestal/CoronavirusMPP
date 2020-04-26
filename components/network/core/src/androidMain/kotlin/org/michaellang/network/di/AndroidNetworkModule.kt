package org.michaellang.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.michaellang.network.AndroidPlatformErrorConverter
import org.michaellang.network.Const
import org.michaellang.network.PlatformErrorConverter
import org.michaellang.network.interceptor.TokenInterceptor
import org.michaellang.network.interceptor.requestInterceptorFeature
import java.util.concurrent.TimeUnit

class AndroidNetworkModule(
    private val json: Json
) : AbstractNetworkModule() {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    override fun getPlatformErrorConverter(): PlatformErrorConverter {
        return AndroidPlatformErrorConverter()
    }

    override fun getHttpClient(tokenInterceptor: TokenInterceptor): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                preconfigured = okHttpClient
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }

            requestInterceptorFeature {
                add(tokenInterceptor)
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    override fun getBaseUrl() = Const.BASE_URL

    companion object {
        private const val CONNECT_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 30L
        private const val READ_TIMEOUT = 30L
    }
}