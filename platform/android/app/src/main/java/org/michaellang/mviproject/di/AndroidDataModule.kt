package org.michaellang.mviproject.di

import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.michaellang.common.DateTimeProvider
import org.michaellang.data.di.DataCoreModule

class AndroidDataModule {
    val module = Kodein.Module("android_data_module") {

    }
}