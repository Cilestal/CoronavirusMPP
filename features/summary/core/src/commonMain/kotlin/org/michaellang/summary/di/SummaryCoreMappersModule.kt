package org.michaellang.summary.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.summary.presentation.mapper.CountrySummaryListModelMapper
import org.michaellang.summary.presentation.mapper.GlobalSummaryModelMapper
import org.michaellang.summary.presentation.mapper.SummaryListSortModelMapper

class SummaryCoreMappersModule {
    val module = DI.Module("summary_mappers_core_module") {
        bind<GlobalSummaryModelMapper>() with singleton { GlobalSummaryModelMapper(instance()) }
        bind<CountrySummaryListModelMapper>() with singleton { CountrySummaryListModelMapper() }
        bind<SummaryListSortModelMapper>() with singleton { SummaryListSortModelMapper(instance()) }
    }
}