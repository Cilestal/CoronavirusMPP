package org.michaellang.network.di

import io.ktor.client.*
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.common.annotation.RequiredBindings
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.network.NetworkErrorConverter
import org.michaellang.network.NetworkErrorConverterImpl
import org.michaellang.network.NetworkService
import org.michaellang.network.NetworkServiceImpl
import org.michaellang.network.PlatformErrorConverter
import org.michaellang.network.interceptor.TokenInterceptor

@RequiredBindings(
    Json::class
)
abstract class AbstractNetworkModule : KodeinModuleHolder {

    override val module = DI.Module("core_network_module") {
        import(InterceptorsModule().module)

        bind<NetworkService>() with singleton {
            NetworkServiceImpl(instance(), instance(TAG_HTTP_CLIENT), instance(TAG_BASE_URL))
        }
        bind<NetworkErrorConverter>() with singleton {
            NetworkErrorConverterImpl(instance(TAG_PLATFORM_ERROR_CONVERTER))
        }

        // platform
        bind<PlatformErrorConverter>(TAG_PLATFORM_ERROR_CONVERTER) with singleton { getPlatformErrorConverter() }
        bind<HttpClient>(TAG_HTTP_CLIENT) with singleton { getHttpClient(instance(), instance()) }
        bind<String>(TAG_BASE_URL) with singleton { getBaseUrl() }
    }

    abstract fun getPlatformErrorConverter(): PlatformErrorConverter
    abstract fun getHttpClient(tokenInterceptor: TokenInterceptor, json: Json): HttpClient
    abstract fun getBaseUrl(): String

    companion object {
        private const val TAG_BASE_URL = "base_url"
        private const val TAG_PLATFORM_ERROR_CONVERTER = "platform_error_converter"
        private const val TAG_HTTP_CLIENT = "http_client"
    }
}