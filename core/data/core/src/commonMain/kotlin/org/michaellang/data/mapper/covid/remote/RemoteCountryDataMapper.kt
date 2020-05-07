package org.michaellang.data.mapper.covid.remote

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.CountryData
import org.michaellang.network.dto.CountryDto

internal class RemoteCountryDataMapper : Mapper<CountryDto, CountryData> {

    override fun map(data: CountryDto): CountryData {
        return CountryData(
            country = data.country,
            iso2 = data.iso2
        )
    }
}