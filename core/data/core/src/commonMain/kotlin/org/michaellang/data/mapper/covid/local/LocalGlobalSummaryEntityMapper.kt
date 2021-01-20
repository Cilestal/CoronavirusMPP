package org.michaellang.data.mapper.covid.local

import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.data.Const.DATE_FORMAT
import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.GlobalSummaryData
import org.michaellang.database.GlobalSummaryEntity

class LocalGlobalSummaryEntityMapper(
    private val localDateTimeProvider: DateTimeProvider
) : Mapper<GlobalSummaryData, GlobalSummaryEntity> {

    override fun map(data: GlobalSummaryData): GlobalSummaryEntity {
        return GlobalSummaryEntity(
            date = localDateTimeProvider.format(data.date, DATE_FORMAT),
            totalRecovered = data.totalRecovered,
            totalDeaths = data.totalDeaths,
            totalConfirmed = data.totalConfirmed,
            newRecovered = data.newRecovered,
            newDeaths = data.newDeaths,
            newConfirmed = data.newConfirmed
        )
    }

}