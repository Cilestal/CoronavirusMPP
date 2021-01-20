package org.michaellang.summary.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.summary.domain.interactor.SummaryInteractor
import org.michaellang.summary.domain.interactor.SummaryInteractorImpl
import org.michaellang.summary.domain.interactor.SummaryListSortInteractor
import org.michaellang.summary.domain.interactor.SummaryListSortInteractorImpl

class SummaryDomainModule : KodeinModuleHolder {
    override val module = DI.Module("summary_domain_module") {
        import(SummaryDomainMappersModule().module)
        bind<SummaryInteractor>() with singleton {
            SummaryInteractorImpl(instance(), instance(), instance())
        }

        bind<SummaryListSortInteractor>() with singleton {
            SummaryListSortInteractorImpl(instance())
        }
    }
}