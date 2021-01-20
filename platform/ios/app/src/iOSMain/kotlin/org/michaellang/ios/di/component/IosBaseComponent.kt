package org.michaellang.ios.di.component

import org.kodein.di.DI
import org.kodein.di.instance
import org.michaellang.common.base.BaseViewModel
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.common.extensions.import
import org.michaellang.ios.di.Injector
import org.michaellang.ios.di.IosCommonModule

abstract class IosBaseComponent<T : BaseViewModel> : KodeinModuleHolder {
    val kodein = DI.lazy {
        extend(Injector.di)
        import(IosCommonModule())
        import(module)
    }

    abstract val viewModel: T
}