package org.michaellang.common.test

import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.michaellang.common.di.KodeinModuleHolder

abstract class BaseKodeinTestImpl<T : KodeinModuleHolder> : DIAware {

    override val di = DI.lazy {
        import(sut.module)
    }

    protected abstract var sut: T

    protected inline fun <reified T : Any> DIAware.test(): T {
        val instance: T by instance<T>()
        return instance
    }
}