package org.michaellang.data.datasource.local.mapper.covid

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.statistic.CountryData
import org.michaellang.database.CountryEntity

class LocalCountryDataMapper : Mapper<CountryEntity, CountryData> {
    override fun map(data: CountryEntity): CountryData {
        return CountryData(
            country = data.country,
            iso2 = data.iso2
        )
    }
}