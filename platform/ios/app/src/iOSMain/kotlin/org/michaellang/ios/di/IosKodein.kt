package org.michaellang.ios.di

import org.kodein.di.Kodein
import org.michaellang.common.di.IosProviderModule
import org.michaellang.common.di.JsonModule
import org.michaellang.common.provider.DateTimeProvider

class IosKodein(
    private val dateTimeProvider: DateTimeProvider
) {
    val kodein: Kodein by Kodein.lazy {
        import(JsonModule().module)
        import(IosProviderModule(dateTimeProvider).module)
        import(IosDataModule().module)
    }
}