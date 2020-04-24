package org.michaellang.database.dao

import org.michaellang.database.CountryEntity
import org.michaellang.database.CountryEntityQueries

class CountryDaoImpl(
    private val queries: CountryEntityQueries
) : CountryDao {

    override fun insertCountries(list: List<CountryEntity>) {
        queries.transaction {
            list.forEach(queries::insert)
        }
    }

    override fun getCountry(iso2: String): CountryEntity? {
        return queries.getCountry(iso2).executeAsOneOrNull()
    }

    override fun getCountryList(): List<CountryEntity> {
        return queries.getCountryList().executeAsList()
    }

    override fun clear() {
        queries.clear()
    }

}