package org.michaellang.ios.di

import kotlinx.serialization.json.Json
import org.michaellang.common.extensions.test
import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.common.provider.ResourceProvider
import org.michaellang.data.repository.CovidRepository
import org.michaellang.database.AppDatabase
import org.michaellang.home.HomeNavigator
import org.michaellang.network.NetworkService
import kotlin.test.Test

class IosKodeinTest {

    private val sut = Injector.apply {
        setup()
    }

    @Test
    fun testNetworkService() {
        sut.test<NetworkService>()
    }

    @Test
    fun testJson() {
        sut.test<Json>()
    }

    @Test
    fun testDateTimeProvider() {
        sut.test<DateTimeProvider>()
    }

    @Test
    fun testResourceProvider() {
        sut.test<ResourceProvider>()
    }

    @Test
    fun testAppDatabase() {
        sut.test<AppDatabase>()
    }

    @Test
    fun testCovidRepository() {
        sut.test<CovidRepository>()
    }

    @Test
    fun testHomeNavigator() {
        sut.test<HomeNavigator>()

        throw Exception()
    }
}