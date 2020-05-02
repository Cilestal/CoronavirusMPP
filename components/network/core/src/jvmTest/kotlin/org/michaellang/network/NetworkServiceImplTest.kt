package org.michaellang.network

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.UnstableDefault
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NetworkServiceImplTest {

    @MockK
    private lateinit var networkErrorConverter: NetworkErrorConverter

    private lateinit var sut: NetworkServiceImpl

    @UnstableDefault
    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        val httpClient = HttpClientProvider().client
        sut = NetworkServiceImpl(networkErrorConverter, httpClient, BASE_URL)
    }

    @Test
    fun getCountryList() {
        runBlocking {
            val countryList = sut.getCountryList()
            assertTrue(countryList.isNotEmpty())
        }
    }

    @Test
    fun getSummary() {
        runBlocking {
            sut.getSummary()
        }
    }

    @Test
    fun getDayOneTotal() {
        runBlocking {
            val dayOne = sut.getDayOneTotal("")
            assertTrue(dayOne.isNotEmpty())
        }
    }

    companion object {
        private const val BASE_URL = "base_url"
    }
}