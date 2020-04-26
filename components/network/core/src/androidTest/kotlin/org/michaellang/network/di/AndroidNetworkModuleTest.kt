package org.michaellang.network.di

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance
import org.michaellang.network.NetworkService

class AndroidNetworkModuleTest : KodeinAware {

    override val kodein = Kodein.lazy {
        import(getModule())
    }

    @MockK
    private lateinit var json: Json

    private lateinit var sut: AndroidNetworkModule

    @Before
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        sut = AndroidNetworkModule(json)
    }

    @Test
    fun initModule() {
        val instance = instance<NetworkService>()
        println(instance)
    }

    private fun getModule() = sut.module
}