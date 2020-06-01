package org.michaellang.data.mapper.covid.local

import org.michaellang.common.provider.DateTimeProvider
import org.michaellang.data.Const.DATE_FORMAT
import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.GlobalSummaryData
import org.michaellang.database.GlobalSummaryEntity

class LocalGlobalSummaryDataMapper(
    private val localDateTimeProvider: DateTimeProvider
) : Mapper<GlobalSummaryEntity, GlobalSummaryData> {
    override fun map(data: GlobalSummaryEntity): GlobalSummaryData {
        return GlobalSummaryData(
            newConfirmed = data.newConfirmed,
            newDeaths = data.newDeaths,
            newRecovered = data.newRecovered,
            totalConfirmed = data.totalConfirmed,
            totalDeaths = data.totalDeaths,
            totalRecovered = data.totalRecovered,
            date = localDateTimeProvider.parse(data.date, DATE_FORMAT)
        )
    }
}