package org.michaellang.common.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import org.michaellang.common.base.CancelableCoroutineScope

class CoroutineScopeCoreModule {
    val module = Kodein.Module("coroutine_scope_core_module") {
        bind<CoroutineScope>() with singleton {
            CancelableCoroutineScope(Dispatchers.Main)
        }
    }
}