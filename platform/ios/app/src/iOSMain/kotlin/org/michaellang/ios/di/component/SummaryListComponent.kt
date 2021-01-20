package org.michaellang.ios.di.component

import org.kodein.di.DI
import org.kodein.di.instance
import org.michaellang.summary.di.SummaryDomainModule
import org.michaellang.summary.presentation.list.SummaryListViewModel

class SummaryListComponent : IosBaseComponent<SummaryListViewModel>() {

    override val viewModel by kodein.instance<SummaryListViewModel>()

    override val module = DI.Module("iOS_summary_list_module") {
        //import(SummaryListCoreModule().module)
        import(SummaryDomainModule().module)
    }
}