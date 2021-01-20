package org.michaellang.common.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.michaellang.common.base.CancelableCoroutineScope

class CoroutineScopeCoreModule {
    val module = DI.Module("coroutine_scope_core_module") {
        bind<CoroutineScope>() with singleton {
            CancelableCoroutineScope(Dispatchers.Main)
        }
    }
}