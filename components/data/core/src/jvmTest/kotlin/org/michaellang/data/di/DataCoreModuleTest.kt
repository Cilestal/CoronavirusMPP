package org.michaellang.data.di

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.michaellang.common.DateTimeProvider
import org.michaellang.common.test.KodeinTest
import org.michaellang.data.repository.CovidRepository
import org.michaellang.database.dao.CountryDao
import org.michaellang.database.dao.CountrySummaryDao
import org.michaellang.database.dao.GlobalSummaryDao
import org.michaellang.network.NetworkService

class DataCoreModuleTest : KodeinTest<DataCoreModule>() {
    @MockK
    private lateinit var dateTimeProvider: DateTimeProvider

    @MockK
    private lateinit var networkService: NetworkService

    @MockK
    private lateinit var countryDao: CountryDao

    @MockK
    private lateinit var countrySummaryDao: CountrySummaryDao

    @MockK
    private lateinit var globalSummaryDao: GlobalSummaryDao

    override lateinit var sut: DataCoreModule

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        sut = DataCoreModule(
            dateTimeProvider, networkService, countryDao, countrySummaryDao, globalSummaryDao
        )
    }

    @Test
    fun testCovidRepository() {
        test<CovidRepository>()
    }

}