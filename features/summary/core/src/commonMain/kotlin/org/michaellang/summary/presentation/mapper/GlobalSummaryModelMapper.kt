package org.michaellang.summary.presentation.mapper

import org.michaellang.common.MR
import org.michaellang.common.provider.ResourceProvider
import org.michaellang.data.mapper.Mapper
import org.michaellang.summary.domain.model.GlobalSummaryDomain
import org.michaellang.summary.presentation.model.GlobalSummaryModel

class GlobalSummaryModelMapper(
    private val res: ResourceProvider
) : Mapper<GlobalSummaryDomain, List<GlobalSummaryModel>> {

    override fun map(data: GlobalSummaryDomain): List<GlobalSummaryModel> {
        return listOf(
            GlobalSummaryModel(
                title = res.getString(MR.strings.global_summary_new_confirmed_title),
                value = data.newConfirmed
            ),
            GlobalSummaryModel(
                title = res.getString(MR.strings.global_summary_total_confirmed_title),
                value = data.totalConfirmed
            ),
            GlobalSummaryModel(
                title = res.getString(MR.strings.global_summary_new_deaths_title),
                value = data.newDeaths
            ),
            GlobalSummaryModel(
                title = res.getString(MR.strings.global_summary_total_deaths_title),
                value = data.totalDeaths
            ),
            GlobalSummaryModel(
                title = res.getString(MR.strings.global_summary_new_recovered_title),
                value = data.newRecovered
            ),
            GlobalSummaryModel(
                title = res.getString(MR.strings.global_summary_total_recovered_title),
                value = data.totalRecovered
            )
        )
    }

}