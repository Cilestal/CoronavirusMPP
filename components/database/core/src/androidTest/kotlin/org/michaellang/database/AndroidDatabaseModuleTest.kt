package org.michaellang.database

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instanceOrNull
import org.michaellang.database.dao.CountryDao

class AndroidDatabaseModuleTest : KodeinAware {

    override val kodein = Kodein.lazy {
        import(getModule())
    }

    @MockK
    private lateinit var context: Context

    private lateinit var sut: AndroidDatabaseModule

    @Before
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = AndroidDatabaseModule(context)
    }

    @Test
    fun testDI() {
        val instance: CountryDao? by instanceOrNull<CountryDao>()
        assertNotNull(instance)
    }

    private fun getModule() = sut.module
}