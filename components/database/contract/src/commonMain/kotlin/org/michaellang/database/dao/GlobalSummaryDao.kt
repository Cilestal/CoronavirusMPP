package org.michaellang.database.dao

import org.michaellang.database.GlobalSummaryEntity

interface GlobalSummaryDao {
    fun insertSummary(data: GlobalSummaryEntity)
    fun getSummary(date: String)
    fun clear()
}