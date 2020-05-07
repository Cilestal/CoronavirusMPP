package org.michaellang.network.interceptor

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.util.pipeline.PipelineContext

class TokenInterceptor : RequestInterceptor {

    override suspend fun intercept(
        subject: Any,
        pipeline: PipelineContext<Any, HttpRequestBuilder>
    ) {
        // example

        pipeline.proceedWith(subject)
    }

}