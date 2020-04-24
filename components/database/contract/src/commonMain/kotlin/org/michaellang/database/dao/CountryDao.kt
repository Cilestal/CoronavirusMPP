package org.michaellang.database.dao

import org.michaellang.database.CountryEntity

interface CountryDao {
    fun insertCountries(list: List<CountryEntity>)
    fun getCountry(iso2: String): CountryEntity?
    fun getCountryList(): List<CountryEntity>
    fun clear()
}