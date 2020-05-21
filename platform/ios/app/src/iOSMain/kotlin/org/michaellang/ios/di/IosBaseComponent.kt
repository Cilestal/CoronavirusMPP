package org.michaellang.ios.di

import org.kodein.di.Kodein

abstract class IosBaseComponent(
    parentKodein: Kodein
) {
    val kodein = Kodein.lazy {
        extend(parentKodein)
        import(IosCommonModule().module)
        bind()
    }

    abstract fun Kodein.MainBuilder.bind()

}