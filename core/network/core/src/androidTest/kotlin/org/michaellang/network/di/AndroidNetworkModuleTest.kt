package org.michaellang.network.di

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.common.test.BaseKodeinTestImpl
import org.michaellang.network.NetworkErrorConverter
import org.michaellang.network.NetworkService

class AndroidNetworkModuleTest : BaseKodeinTestImpl<KodeinModuleHolder>() {

    override lateinit var sut: KodeinModuleHolder

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = object : KodeinModuleHolder {
            override val module = DI.Module("sw") {
                import(AndroidNetworkModule().module)
                bind<Json>() with singleton { mockk() }
            }
        }
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