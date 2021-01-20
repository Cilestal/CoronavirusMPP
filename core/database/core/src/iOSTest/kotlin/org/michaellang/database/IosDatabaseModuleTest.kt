package org.michaellang.database

import org.michaellang.common.test.BaseKodeinTestImpl
import org.michaellang.database.dao.CountryDao
import org.michaellang.database.dao.IosDatabaseModule
import kotlin.test.BeforeTest
import kotlin.test.Test

class IosDatabaseModuleTest : BaseKodeinTestImpl<IosDatabaseModule>() {

    override lateinit var sut: IosDatabaseModule

    @BeforeTest
    internal fun setUp() {
        sut = IosDatabaseModule()
    }

    @Test
    fun testDI() {
        test<CountryDao>()
    }

}