package org.michaellang.data.di

import DateTimeProvider
import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.michaellang.common.test.KodeinTest
import org.michaellang.data.repository.CovidRepository

class AndroidDataModuleTest : KodeinTest<AndroidDataModule>() {
    @MockK
    private lateinit var json: Json

    @MockK
    private lateinit var appContext: Context

    @MockK
    private lateinit var dateTimeProvider: DateTimeProvider

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = AndroidDataModule(json, appContext, dateTimeProvider)
    }

    @Test
    fun testCovidRepository() {
        test<CovidRepository>()
    }
}