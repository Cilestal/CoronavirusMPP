package org.michaellang.database.dao

import org.michaellang.database.GlobalSummaryEntity
import org.michaellang.database.GlobalSummaryEntityQueries

class GlobalSummaryDaoImpl(
    private val queries: GlobalSummaryEntityQueries
) : GlobalSummaryDao {

    override fun insertSummary(data: GlobalSummaryEntity) {
        queries.insert(data)
    }

    override fun getSummary(date: String) {
        queries.getSummary(date)
    }

    override fun clear() {
        queries.clear()
    }

}