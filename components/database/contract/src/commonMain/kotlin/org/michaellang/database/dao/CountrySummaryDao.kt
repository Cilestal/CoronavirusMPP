package org.michaellang.database.dao

import org.michaellang.database.CountrySummaryEntity

interface CountrySummaryDao {
    fun insertSummaries(list: List<CountrySummaryEntity>)
    fun getSummary(country: String, date: String): CountrySummaryEntity?
    fun clear()
}