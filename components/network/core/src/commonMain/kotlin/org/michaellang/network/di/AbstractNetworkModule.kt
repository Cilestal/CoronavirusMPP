package org.michaellang.network.di

import io.ktor.client.HttpClient
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.network.*
import org.michaellang.network.interceptor.TokenInterceptor

abstract class AbstractNetworkModule : KodeinModuleHolder {

    override val module = Kodein.Module("core_network_module") {
        import(InterceptorsModule().module)

        bind<NetworkService>() with singleton {
            NetworkServiceImpl(instance(), instance(TAG_HTTP_CLIENT), instance(TAG_BASE_URL))
        }
        bind<NetworkErrorConverter>() with singleton {
            NetworkErrorConverterImpl(instance(TAG_PLATFORM_ERROR_CONVERTER))
        }

        // platform
        bind<PlatformErrorConverter>(TAG_PLATFORM_ERROR_CONVERTER) with singleton { getPlatformErrorConverter() }
        bind<HttpClient>(TAG_HTTP_CLIENT) with singleton { getHttpClient(instance()) }
        bind<String>(TAG_BASE_URL) with singleton { getBaseUrl() }
    }

    abstract fun getPlatformErrorConverter(): PlatformErrorConverter
    abstract fun getHttpClient(tokenInterceptor: TokenInterceptor): HttpClient
    abstract fun getBaseUrl(): String

    companion object {
        private const val TAG_BASE_URL = "base_url"
        private const val TAG_PLATFORM_ERROR_CONVERTER = "platform_error_converter"
        private const val TAG_HTTP_CLIENT = "http_client"
    }
}