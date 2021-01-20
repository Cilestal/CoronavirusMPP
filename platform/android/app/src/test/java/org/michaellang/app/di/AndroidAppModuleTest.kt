package org.michaellang.app.di

import android.app.Application
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.michaellang.network.NetworkService
import org.michaellang.common.extensions.test

class AndroidAppModuleTest {

    @MockK
    private lateinit var application: Application
    private lateinit var sut: AppModule

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = AppModule(application)
    }

    @Test
    fun testNetworkService() {
        val q = sut.test<NetworkService>()
    }


}