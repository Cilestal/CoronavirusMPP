package org.michaellang.network.interceptor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.statement.*
import io.ktor.util.*
import io.ktor.util.pipeline.*

interface ResponseInterceptor {
    suspend fun intercept(subject: Any, pipeline: PipelineContext<HttpResponse, HttpClientCall>)
}

class ResponseInterceptorFeature(
    private val interceptors: List<ResponseInterceptor>
) {
    class Config {
        internal val interceptors: MutableList<ResponseInterceptor> = mutableListOf()

        fun add(interceptor: ResponseInterceptor) {
            interceptors += interceptor
        }
    }

    companion object : HttpClientFeature<Config, ResponseInterceptorFeature> {
        override val key: AttributeKey<ResponseInterceptorFeature> =
            AttributeKey("ResponseInterceptorFeature")

        override fun prepare(block: Config.() -> Unit): ResponseInterceptorFeature {
            val config = Config().apply(block)
            return ResponseInterceptorFeature(config.interceptors)
        }

        override fun install(feature: ResponseInterceptorFeature, scope: HttpClient) {
            feature.interceptors.forEach { interceptor ->
                scope.receivePipeline.intercept(HttpReceivePipeline.After) {
                    interceptor.intercept(it, this)
                }
            }
        }
    }
}

fun HttpClientConfig<*>.responseInterceptorFeature(block: ResponseInterceptorFeature.Config.() -> Unit) {
    install(ResponseInterceptorFeature, block)
}