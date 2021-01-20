package org.michaellang.common.extensions

import org.kodein.di.DI
import org.michaellang.common.di.KodeinModuleHolder

fun DI.Builder.import(holder: KodeinModuleHolder) {
    import(holder.module)
}