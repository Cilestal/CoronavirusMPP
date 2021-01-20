package org.michaellang.database

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.michaellang.common.test.BaseKodeinTestImpl
import org.michaellang.database.dao.CountryDao

class AndroidDatabaseModuleTest : BaseKodeinTestImpl<AndroidDatabaseModule>() {

    @MockK
    private lateinit var context: Context
    override lateinit var sut: AndroidDatabaseModule

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = AndroidDatabaseModule(context)
    }

    @Test
    fun testDI() {
        test<CountryDao>()
    }

}