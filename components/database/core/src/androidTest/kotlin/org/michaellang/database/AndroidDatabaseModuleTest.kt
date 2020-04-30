package org.michaellang.database

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.michaellang.common.test.KodeinTest
import org.michaellang.database.dao.CountryDao

class AndroidDatabaseModuleTest : KodeinTest<AndroidDatabaseModule>() {

    @MockK
    private lateinit var context: Context

    @Before
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = AndroidDatabaseModule(context)
    }

    @Test
    fun testDI() {
        test<CountryDao>()
    }

}