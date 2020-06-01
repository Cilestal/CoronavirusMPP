package org.michaellang.ios.di.component

import org.kodein.di.Kodein
import org.kodein.di.erased.instance
import org.michaellang.ios.di.IosBaseComponent
import org.michaellang.summary.di.SummaryDomainModule
import org.michaellang.summary.di.SummaryListCoreModule
import org.michaellang.summary.presentation.list.SummaryListViewModel

class SummaryListComponent(
    parentKodein: Kodein
) : IosBaseComponent<SummaryListViewModel>(parentKodein) {

    override val viewModel by kodein.instance<SummaryListViewModel>()

    override fun Kodein.MainBuilder.bind() {
        import(SummaryListCoreModule().module)
        import(SummaryDomainModule().module)
    }
}