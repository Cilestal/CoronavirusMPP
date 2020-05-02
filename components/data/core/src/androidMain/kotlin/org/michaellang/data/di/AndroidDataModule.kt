package org.michaellang.data.di

import DateTimeProvider
import android.content.Context
import kotlinx.serialization.json.Json
import org.michaellang.data.datasource.di.AbstractDataCoreModule
import org.michaellang.database.AndroidDatabaseModule
import org.michaellang.network.di.AndroidNetworkModule

class AndroidDataModule(
    private val json: Json,
    private val appContext: Context,
    private val dateTimeProvider: DateTimeProvider
) : AbstractDataCoreModule() {

    override fun getDateTimeProvider() = dateTimeProvider

    override fun getNetworkModule() = AndroidNetworkModule(json)

    override fun getDatabaseModule() = AndroidDatabaseModule(appContext)

}