package org.michaellang.data.datasource

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.michaellang.data.datasource.remote.CovidDataSourceRemote
import org.michaellang.data.datasource.remote.mapper.covid.RemoteCountryDataMapper
import org.michaellang.data.datasource.remote.mapper.covid.RemoteDayOneDataMapper
import org.michaellang.data.datasource.remote.mapper.covid.RemoteSummaryDataMapper
import org.michaellang.network.NetworkService

class CovidDataSourceRemoteTest {

    @MockK
    private lateinit var networkService: NetworkService

    @MockK
    private lateinit var countryDataMapper: RemoteCountryDataMapper

    @MockK
    private lateinit var summaryDataMapper: RemoteSummaryDataMapper

    @MockK
    private lateinit var dayOneDataMapper: RemoteDayOneDataMapper

    private lateinit var sut: CovidDataSourceRemote

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        sut = CovidDataSourceRemote(
            networkService, countryDataMapper, summaryDataMapper, dayOneDataMapper
        )
    }

}