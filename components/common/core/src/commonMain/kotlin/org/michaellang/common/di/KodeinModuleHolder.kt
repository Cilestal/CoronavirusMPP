package org.michaellang.common.di

import org.kodein.di.Kodein

interface KodeinModuleHolder {
    val module: Kodein.Module
}