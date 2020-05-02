package org.michaellang.data.di

import DateTimeProvider
import kotlinx.serialization.json.Json
import org.michaellang.data.datasource.di.AbstractDataCoreModule
import org.michaellang.database.dao.IosDatabaseModule
import org.michaellang.network.di.*

class AndroidDataModule(
    private val json: Json,
    private val dateTimeProvider: DateTimeProvider
) : AbstractDataCoreModule() {

    override fun getDateTimeProvider() = dateTimeProvider

    override fun getNetworkModule() = IosNetworkModule(json)

    override fun getDatabaseModule() = IosDatabaseModule()

}