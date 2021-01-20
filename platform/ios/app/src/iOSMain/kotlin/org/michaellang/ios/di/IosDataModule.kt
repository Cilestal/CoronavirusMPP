package org.michaellang.ios.di

import org.kodein.di.DI
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.data.di.DataCoreModule
import org.michaellang.database.dao.IosDatabaseModule
import org.michaellang.network.di.IosNetworkModule

internal class IosDataModule : KodeinModuleHolder {
    override val module = DI.Module("ios_data_module") {
        import(IosDatabaseModule().module)
        import(IosNetworkModule().module)
        import(DataCoreModule().module)
    }
}