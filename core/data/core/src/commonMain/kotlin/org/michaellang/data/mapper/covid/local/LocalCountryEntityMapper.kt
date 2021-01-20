package org.michaellang.data.mapper.covid.local

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.CountryData
import org.michaellang.database.CountryEntity

class LocalCountryEntityMapper : Mapper<CountryData, CountryEntity> {
    override fun map(data: CountryData): CountryEntity {
        return CountryEntity(
            country = data.country,
            iso2 = data.iso2
        )
    }
}