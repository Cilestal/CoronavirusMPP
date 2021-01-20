package org.michaellang.common.di

import org.kodein.di.DI

interface KodeinModuleHolder {
    val module: DI.Module
}