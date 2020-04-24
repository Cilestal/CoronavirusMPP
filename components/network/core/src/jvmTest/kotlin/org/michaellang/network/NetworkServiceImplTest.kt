package org.michaellang.network

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.MockitoAnnotations

internal class NetworkServiceImplTest {

    private lateinit var sut: NetworkServiceImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)


        val httpClient = HttpClientProvider().client
        sut = NetworkServiceImpl(httpClient, BASE_URL)
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
            val summary = sut.getSummary()
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