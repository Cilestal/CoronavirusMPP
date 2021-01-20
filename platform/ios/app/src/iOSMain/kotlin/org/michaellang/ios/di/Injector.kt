package org.michaellang.ios.di

import org.kodein.di.DI
import org.kodein.di.DIAware
import org.michaellang.common.extensions.import
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object Injector : DIAware {
    override lateinit var di: DI

    fun setup() {
        if (!this::di.isInitialized) {
            di = DI.lazy {
                import(IosAppModule())
            }
        }
    }
}