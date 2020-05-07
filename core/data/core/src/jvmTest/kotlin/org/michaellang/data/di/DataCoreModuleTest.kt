package org.michaellang.data.di

import io.mockk.MockKAnnotations
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.michaellang.common.test.KodeinTest
import org.michaellang.data.repository.CovidRepository

class DataCoreModuleTest : KodeinTest<DataCoreModule>() {
    override lateinit var sut: DataCoreModule

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        sut = DataCoreModule(

        )
    }

    @Test
    fun testCovidRepository() {
        test<CovidRepository>()
    }

}