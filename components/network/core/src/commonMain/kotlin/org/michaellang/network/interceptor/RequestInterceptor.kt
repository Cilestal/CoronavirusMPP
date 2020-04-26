package org.michaellang.network.interceptor

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.features.HttpClientFeature
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.util.AttributeKey
import io.ktor.util.pipeline.PipelineContext

interface RequestInterceptor {
    suspend fun intercept(subject: Any, pipeline: PipelineContext<Any, HttpRequestBuilder>)
}

class RequestInterceptorFeature(
    private val interceptors: List<RequestInterceptor>
) {
    class Config {
        internal val retryHandlers: MutableList<RequestInterceptor> = mutableListOf()

        fun add(interceptor: RequestInterceptor) {
            retryHandlers += interceptor
        }
    }

    companion object : HttpClientFeature<Config, RequestInterceptorFeature> {
        override val key: AttributeKey<RequestInterceptorFeature> =
            AttributeKey("RequestInterceptorFeature")

        override fun prepare(block: Config.() -> Unit): RequestInterceptorFeature {
            val config = Config().apply(block)
            return RequestInterceptorFeature(config.retryHandlers)
        }

        override fun install(feature: RequestInterceptorFeature, scope: HttpClient) {
            feature.interceptors.forEach { interceptor ->
                scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                    interceptor.intercept(it, this)
                }
            }
        }
    }
}

fun HttpClientConfig<*>.requestInterceptorFeature(block: RequestInterceptorFeature.Config.() -> Unit) {
    install(RequestInterceptorFeature, block)
}