package org.michaellang.network

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.ktor.client.features.SocketTimeoutException
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.michaellang.test.BaseTest
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class BaseErrorConverterTest : BaseTest() {

    @Mock
    private lateinit var platformErrorConverter: PlatformErrorConverter

    private lateinit var sut: NetworkErrorConverterImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        sut = NetworkErrorConverterImpl(platformErrorConverter)
    }

    @Test
    fun `mapThrowable should invoke systemErrorConverter`() {
        runBlocking {
            val socketTimeoutException = SocketTimeoutException(mock(), mock())
            sut.mapThrowable(socketTimeoutException)
            verify(platformErrorConverter).mapThrowable(socketTimeoutException)
        }
    }

    @Test
    fun `mapThrowable should return UnclassifiedException`() {
        runBlocking {
            val socketTimeoutException = SocketTimeoutException(mock(), mock())
            sut.mapThrowable(socketTimeoutException)
            verify(platformErrorConverter).mapThrowable(socketTimeoutException)
        }
    }
}