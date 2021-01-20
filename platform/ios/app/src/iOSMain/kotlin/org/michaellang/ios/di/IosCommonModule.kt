package org.michaellang.ios.di

import org.kodein.di.DI
import org.michaellang.common.di.CoroutineScopeCoreModule
import org.michaellang.common.di.KodeinModuleHolder

class IosCommonModule : KodeinModuleHolder {
    override val module = DI.Module("ios_common_module") {
        import(CoroutineScopeCoreModule().module)
    }
}