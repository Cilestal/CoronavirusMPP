package org.michaellang.summary.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.MergeAdapter
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
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
    override val module = DI.Module("summary_list_android_module") {
        import(SummaryDomainModule().module)
        import(SummaryCoreMappersModule().module)

        bind<SummaryListViewModel>() with singleton { fragment.vm<SummaryListViewModelImpl>(instance()) }

        bind<GlobalSummaryAdapter>() with singleton {
            GlobalSummaryAdapter(instance())
        }

        bind<CountryListSummaryAdapter>() with singleton {
            CountryListSummaryAdapter(instance(), instance(arg = fragment.requireContext()))
        }

        bind<MergeAdapter>() with singleton { MergeAdapter() }

        bind<ViewModelProvider.Factory>() with singleton {
            vmFactory {
                SummaryListViewModelImpl(
                    instance(), instance(), instance(), instance(), instance(),
                    instance(), instance()
                )
            }
        }
    }
}