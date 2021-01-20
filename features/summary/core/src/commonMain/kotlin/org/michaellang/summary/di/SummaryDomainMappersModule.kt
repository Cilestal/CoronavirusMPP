package org.michaellang.summary.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.summary.domain.mapper.CountrySummaryDomainMapper
import org.michaellang.summary.domain.mapper.GlobalSummaryDomainMapper

class SummaryDomainMappersModule : KodeinModuleHolder {
    override val module = DI.Module("summary_domain_mappers_module") {
        bind<CountrySummaryDomainMapper>() with singleton {
            CountrySummaryDomainMapper()
        }
        bind<GlobalSummaryDomainMapper>() with singleton {
            GlobalSummaryDomainMapper()
        }
    }
}