package org.michaellang.app.di

import android.content.Context
import org.kodein.di.Kodein
import org.michaellang.data.di.DataCoreModule
import org.michaellang.database.AndroidDatabaseModule
import org.michaellang.network.di.AndroidNetworkModule

class AndroidDataModule(
    context: Context
) {
    val module = Kodein.Module("android_data_module") {
        import(AndroidDatabaseModule(context).module)
        import(AndroidNetworkModule().module)
        import(DataCoreModule().module)
    }
}