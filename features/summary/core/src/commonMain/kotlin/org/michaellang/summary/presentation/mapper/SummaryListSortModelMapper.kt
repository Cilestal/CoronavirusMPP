package org.michaellang.summary.presentation.mapper

import org.michaellang.common.MR
import org.michaellang.common.provider.ResourceProvider
import org.michaellang.data.mapper.Mapper
import org.michaellang.summary.domain.model.SummaryListSortDomain
import org.michaellang.summary.presentation.model.SummaryListSortModel

class SummaryListSortModelMapper(
    private val res: ResourceProvider
) : Mapper<SummaryListSortDomain, SummaryListSortModel> {

    override fun map(data: SummaryListSortDomain): SummaryListSortModel {
        return SummaryListSortModel(
            title = getSortTitle(data),
            sort = data
        )
    }

    private fun getSortTitle(domain: SummaryListSortDomain): String {
        return when (domain) {
            SummaryListSortDomain.TOTAL_CONFIRMED -> MR.strings.menu_sort_total_confirmed
            SummaryListSortDomain.TOTAL_DEATHS -> MR.strings.menu_sort_total_death
            SummaryListSortDomain.TOTAL_RECOVERED -> MR.strings.menu_sort_total_recovered
        }.let(res::getString)
    }

}