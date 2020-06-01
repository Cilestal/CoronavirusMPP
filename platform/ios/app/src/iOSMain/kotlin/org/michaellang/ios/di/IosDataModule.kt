package org.michaellang.ios.di

import org.kodein.di.Kodein
import org.michaellang.data.di.DataCoreModule
import org.michaellang.database.dao.IosDatabaseModule
import org.michaellang.network.di.IosNetworkModule

internal class IosDataModule {
    val module = Kodein.Module("ios_data_module") {
        import(IosDatabaseModule().module)
        import(IosNetworkModule().module)
        import(DataCoreModule().module)
    }
}