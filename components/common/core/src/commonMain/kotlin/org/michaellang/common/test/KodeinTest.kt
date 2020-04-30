package org.michaellang.common.test

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance
import org.michaellang.common.di.KodeinModuleHolder

abstract class KodeinTest<T : KodeinModuleHolder> : KodeinAware {

    override val kodein = Kodein.lazy {
        import(sut.module)
    }

    protected lateinit var sut: T

    protected inline fun <reified T : Any> test(): T {
        val instance: T by instance<T>()
        return instance
    }
}