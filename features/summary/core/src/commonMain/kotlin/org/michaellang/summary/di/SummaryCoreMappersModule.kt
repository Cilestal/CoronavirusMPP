package org.michaellang.summary.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.summary.presentation.mapper.CountrySummaryListModelMapper
import org.michaellang.summary.presentation.mapper.GlobalSummaryModelMapper

class SummaryCoreMappersModule {
    val module = Kodein.Module("summary_mappers_core_module") {
        bind<GlobalSummaryModelMapper>() with singleton { GlobalSummaryModelMapper(instance()) }
        bind<CountrySummaryListModelMapper>() with singleton { CountrySummaryListModelMapper() }
    }
}