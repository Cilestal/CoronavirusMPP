package org.michaellang.summary.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.summary.domain.mapper.CountrySummaryDomainMapper
import org.michaellang.summary.domain.mapper.GlobalSummaryDomainMapper

class SummaryDomainMappersModule : KodeinModuleHolder {
    override val module = Kodein.Module("summary_domain_mappers_module") {
        bind<CountrySummaryDomainMapper>() with singleton {
            CountrySummaryDomainMapper()
        }
        bind<GlobalSummaryDomainMapper>() with singleton {
            GlobalSummaryDomainMapper()
        }
    }
}