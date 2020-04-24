package org.michaellang.network.di

import io.ktor.client.HttpClient
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.network.*

class NetworkCoreModule(
    errorConverter: PlatformErrorConverter,
    httpClient: HttpClient,
    baseUrl: String
) {
    val module = Kodein.Module("core_network_module") {

        bind<NetworkService>() with singleton {
            NetworkServiceImpl(instance(TAG_HTTP_CLIENT), instance(TAG_BASE_URL))
        }
        bind<ErrorConverter>() with singleton {
            BaseErrorConverter(instance(TAG_PLATFORM_ERROR_CONVERTER))
        }

        // platform
        bind<PlatformErrorConverter>(TAG_PLATFORM_ERROR_CONVERTER) with singleton { errorConverter }
        bind<HttpClient>(TAG_HTTP_CLIENT) with singleton { httpClient }
        bind<String>(TAG_BASE_URL) with singleton { baseUrl }
    }

    companion object {
        private const val TAG_BASE_URL = "base_url"
        private const val TAG_PLATFORM_ERROR_CONVERTER = "platform_error_converter"
        private const val TAG_HTTP_CLIENT = "http_client"
    }
}