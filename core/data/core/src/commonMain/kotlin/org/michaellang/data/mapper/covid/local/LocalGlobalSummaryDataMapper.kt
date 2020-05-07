package org.michaellang.data.mapper.covid.local

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.GlobalSummaryData
import org.michaellang.database.GlobalSummaryEntity

class LocalGlobalSummaryDataMapper : Mapper<GlobalSummaryEntity, GlobalSummaryData> {
    override fun map(data: GlobalSummaryEntity): GlobalSummaryData {
        return GlobalSummaryData(
            newConfirmed = data.newConfirmed,
            newDeaths = data.newDeaths,
            newRecovered = data.newRecovered,
            totalConfirmed = data.totalConfirmed,
            totalDeaths = data.totalDeaths,
            totalRecovered = data.totalRecovered,
            date = data.date
        )
    }
}