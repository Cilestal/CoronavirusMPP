package org.michaellang.network.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import org.michaellang.network.interceptor.TokenInterceptor

internal class InterceptorsModule {
    val module = Kodein.Module("network_interceptors_module") {
        bind<TokenInterceptor>() with singleton {
            TokenInterceptor()
        }
    }
}