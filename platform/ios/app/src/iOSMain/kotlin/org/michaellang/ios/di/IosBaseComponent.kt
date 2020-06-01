package org.michaellang.ios.di

import org.kodein.di.Kodein
import org.michaellang.common.base.BaseViewModel

abstract class IosBaseComponent<T : BaseViewModel>(
    parentKodein: Kodein
) {
    val kodein = Kodein.lazy {
        extend(parentKodein)
        import(IosCommonModule().module)
        bind()
    }

    abstract fun Kodein.MainBuilder.bind()

    abstract val viewModel: T
}