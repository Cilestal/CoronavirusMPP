package org.michaellang.ios.di

import kotlinx.serialization.UnstableDefault
import org.kodein.di.Kodein
import org.michaellang.common.di.IosProviderModule
import org.michaellang.common.di.JsonModule
import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.ios.navigation.NavigationProvider

@OptIn(UnstableDefault::class)
class IosKodein(
    private val dateTimeProvider: DateTimeProvider,
    private val navigationProvider: NavigationProvider
) {
    val kodein: Kodein by Kodein.lazy {
        import(JsonModule().module)
        import(IosProviderModule(dateTimeProvider).module)
        import(IosDataModule().module)
        import(IosNavigationModule(navigationProvider).module)
    }
}