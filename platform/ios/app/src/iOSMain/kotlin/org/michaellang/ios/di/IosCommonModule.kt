package org.michaellang.ios.di

import org.kodein.di.Kodein
import org.michaellang.common.di.CoroutineScopeCoreModule

class IosCommonModule {
    val module = Kodein.Module("ios_common_module") {
        import(CoroutineScopeCoreModule().module)
    }
}