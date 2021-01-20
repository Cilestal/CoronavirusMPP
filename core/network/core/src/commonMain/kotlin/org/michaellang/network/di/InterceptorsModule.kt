package org.michaellang.network.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.michaellang.network.interceptor.TokenInterceptor

internal class InterceptorsModule {
    val module = DI.Module("network_interceptors_module") {
        bind<TokenInterceptor>() with singleton {
            TokenInterceptor()
        }
    }
}