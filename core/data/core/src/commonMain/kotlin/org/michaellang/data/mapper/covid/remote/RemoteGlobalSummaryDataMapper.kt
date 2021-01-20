package org.michaellang.data.mapper.covid.remote

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.covid.GlobalSummaryData
import org.michaellang.network.dto.SummaryDto

class RemoteGlobalSummaryDataMapper(
    private val remoteDateMapper: RemoteDateMapper
) : Mapper<SummaryDto, GlobalSummaryData> {

    override fun map(data: SummaryDto): GlobalSummaryData {
        val global = data.global

        return GlobalSummaryData(
            newConfirmed = global.newConfirmed,
            newDeaths = global.newDeaths,
            newRecovered = global.newRecovered,
            totalConfirmed = global.totalConfirmed,
            totalDeaths = global.totalDeaths,
            totalRecovered = global.totalRecovered,
            date = remoteDateMapper.map(data.date)
        )
    }

}