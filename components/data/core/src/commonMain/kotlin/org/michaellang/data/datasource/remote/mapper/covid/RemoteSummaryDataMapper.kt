package org.michaellang.data.datasource.remote.mapper.covid

import org.michaellang.data.mapper.Mapper
import org.michaellang.data.model.statistic.SummaryData
import org.michaellang.network.dto.SummaryDto

class RemoteSummaryDataMapper(
    private val countrySummaryDataMapper: RemoteCountrySummaryDataMapper,
    private val globalSummaryDataMapper: RemoteGlobalSummaryDataMapper,
    private val remoteDateMapper: RemoteDateMapper
) : Mapper<SummaryDto, SummaryData> {

    override fun map(data: SummaryDto): SummaryData {
        return SummaryData(
            global = globalSummaryDataMapper.map(data),
            countries = data.countries.map(countrySummaryDataMapper::map),
            date = remoteDateMapper.map(data.date)
        )
    }

}