package org.michaellang.app.di

import android.app.Application
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.michaellang.common.test.BaseKodeinTestImpl
import org.michaellang.network.NetworkService

class AndroidAppModuleTest : BaseKodeinTestImpl<AppModule>() {

    @MockK
    private lateinit var application: Application
    override lateinit var sut: AppModule

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = AppModule(application)
    }

    @Test
    fun testNetworkService() {
        test<NetworkService>()
    }
}