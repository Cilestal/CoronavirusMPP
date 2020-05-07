package org.michaellang.database.dao

import org.michaellang.database.CountrySummaryEntity
import org.michaellang.database.CountrySummaryEntityQueries

class CountrySummaryDaoImpl(
    private val queries: CountrySummaryEntityQueries
) : CountrySummaryDao {

    override fun insertSummaries(list: List<CountrySummaryEntity>) {
        queries.transaction {
            list.forEach(queries::insert)
        }
    }

    override fun getSummary(country: String, date: String): CountrySummaryEntity? {
        return queries.getSummary(country, date).executeAsOneOrNull()
    }

    override fun clear() {
        queries.clear()
    }

}