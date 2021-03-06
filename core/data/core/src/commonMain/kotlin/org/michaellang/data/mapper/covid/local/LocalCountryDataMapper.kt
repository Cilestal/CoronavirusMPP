package org.michaellang.data.mapper.covid.local

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.CountryData
import org.michaellang.database.CountryEntity

class LocalCountryDataMapper : Mapper<CountryEntity, CountryData> {
    override fun map(data: CountryEntity): CountryData {
        return CountryData(
            country = data.country,
            iso2 = data.iso2
        )
    }
}