package org.michaellang.network.interceptor

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.HttpClientCall
import io.ktor.client.features.HttpClientFeature
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.HttpResponse
import io.ktor.util.AttributeKey
import io.ktor.util.pipeline.PipelineContext

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