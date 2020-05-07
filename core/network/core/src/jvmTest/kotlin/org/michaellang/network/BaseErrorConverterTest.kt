package org.michaellang.network

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BaseErrorConverterTest {

    @MockK
    private lateinit var platformErrorConverter: PlatformErrorConverter

    private lateinit var sut: NetworkErrorConverterImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        sut = NetworkErrorConverterImpl(platformErrorConverter)
    }

    @Test
    fun `mapThrowable should invoke systemErrorConverter`() {
        runBlocking {
            val socketTimeoutException = Throwable()
            sut.mapThrowable(socketTimeoutException)
            verify { platformErrorConverter.mapThrowable(socketTimeoutException) }
        }
    }

    @Test
    fun `mapThrowable should return UnclassifiedException`() {
        runBlocking {
            val socketTimeoutException = Throwable()
            sut.mapThrowable(socketTimeoutException)
            verify { platformErrorConverter.mapThrowable(socketTimeoutException) }
        }
    }
}