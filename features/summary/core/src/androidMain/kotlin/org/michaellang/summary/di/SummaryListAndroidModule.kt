package org.michaellang.summary.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.common.extensions.vm
import org.michaellang.common.extensions.vmFactory
import org.michaellang.summary.presentation.list.CountryListSummaryAdapter
import org.michaellang.summary.presentation.list.GlobalSummaryAdapter
import org.michaellang.summary.presentation.list.SummaryListViewModel
import org.michaellang.summary.presentation.list.SummaryListViewModelImpl

class SummaryListAndroidModule(
    fragment: Fragment
) : KodeinModuleHolder {
    override val module = Kodein.Module("summary_list_android_module") {
        import(SummaryDomainModule().module)
        import(SummaryCoreMappersModule().module)

        bind<SummaryListViewModel>() with singleton { fragment.vm<SummaryListViewModelImpl>(instance()) }

        bind<GlobalSummaryAdapter>() with singleton {
            GlobalSummaryAdapter(instance())
        }

        bind<CountryListSummaryAdapter>() with singleton {
            CountryListSummaryAdapter(instance(), instance(arg = fragment.requireContext()))
        }

        bind<ViewModelProvider.Factory>() with singleton {
            vmFactory {
                SummaryListViewModelImpl(instance(), instance(), instance(), instance(), instance())
            }
        }
    }
}