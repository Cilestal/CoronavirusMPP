package org.michaellang.common.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.michaellang.common.provider.DateTimeProvider

class ProviderCoreModule : KodeinModuleHolder {
    override val module = DI.Module("provider_core_module") {
        bind<DateTimeProvider>() with singleton {
            object : DateTimeProvider {
                //todo
                override fun getTimeMills() = -1L

                override fun getCurrentDateTime(format: String, timeZone: String?) = ""

                override fun format(date: Long, dateFormat: String) = ""

                override fun parse(date: String, format: String) = -1L

            }
        }
    }
}