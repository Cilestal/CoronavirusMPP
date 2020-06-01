package org.michaellang.summary.di

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.summary.domain.interactor.SummaryInteractor
import org.michaellang.summary.domain.interactor.SummaryInteractorImpl

class SummaryDomainModule : KodeinModuleHolder {
    override val module = Kodein.Module("summary_domain_module") {
        import(SummaryDomainMappersModule().module)
        bind<SummaryInteractor>() with singleton {
            SummaryInteractorImpl(instance(), instance(), instance())
        }
    }
}