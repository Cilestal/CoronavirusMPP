package org.michaellang.data.datasource.local.mapper.covid

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.statistic.CountryData
import org.michaellang.database.CountryEntity

class LocalCountryEntityMapper : Mapper<CountryData, CountryEntity> {
    override fun map(data: CountryData): CountryEntity {
        return CountryEntity.Impl(
            country = data.country,
            iso2 = data.iso2
        )
    }
}