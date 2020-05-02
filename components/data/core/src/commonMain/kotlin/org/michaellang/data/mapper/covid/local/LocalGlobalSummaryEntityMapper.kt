package org.michaellang.data.mapper.covid.local

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.GlobalSummaryData
import org.michaellang.database.GlobalSummaryEntity

class LocalGlobalSummaryEntityMapper : Mapper<GlobalSummaryData, GlobalSummaryEntity> {

    override fun map(data: GlobalSummaryData): GlobalSummaryEntity {
        return GlobalSummaryEntity.Impl(
            date = data.date,
            totalRecovered = data.totalRecovered,
            totalDeaths = data.totalDeaths,
            totalConfirmed = data.totalConfirmed,
            newRecovered = data.newRecovered,
            newDeaths = data.newDeaths,
            newConfirmed = data.newConfirmed
        )
    }

}