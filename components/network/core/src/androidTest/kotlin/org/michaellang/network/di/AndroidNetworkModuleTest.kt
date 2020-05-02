package org.michaellang.network.di

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.michaellang.common.test.KodeinTest
import org.michaellang.network.NetworkErrorConverter
import org.michaellang.network.NetworkService

class AndroidNetworkModuleTest : KodeinTest<AndroidNetworkModule>() {

    @MockK
    private lateinit var json: Json
    override lateinit var sut: AndroidNetworkModule

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = AndroidNetworkModule(json)
    }

    @Test
    fun testNetworkService() {
        test<NetworkService>()
    }

    @Test
    fun testNetworkErrorConverter() {
        test<NetworkErrorConverter>()
    }
}