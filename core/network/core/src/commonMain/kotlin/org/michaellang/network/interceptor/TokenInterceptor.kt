package org.michaellang.network.interceptor

import io.ktor.client.request.*
import io.ktor.util.pipeline.*

class TokenInterceptor : RequestInterceptor {

    override suspend fun intercept(
        subject: Any,
        pipeline: PipelineContext<Any, HttpRequestBuilder>
    ) {
        // example

        pipeline.proceedWith(subject)
    }

}