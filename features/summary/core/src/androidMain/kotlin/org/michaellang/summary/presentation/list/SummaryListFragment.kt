package org.michaellang.summary.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_summary_list.*
import kotlinx.android.synthetic.main.view_country_summary_list.*
import kotlinx.android.synthetic.main.view_global_summary.*
import org.kodein.di.erased.instance
import org.michaellang.common.base.BaseFragment
import org.michaellang.common.extensions.setOnClickListener
import org.michaellang.common.extensions.show
import org.michaellang.common.presentation.dialog.DialogModel
import org.michaellang.summary.R
import org.michaellang.summary.di.SummaryListAndroidModule
import org.michaellang.summary.presentation.model.CountrySummaryListModel
import org.michaellang.summary.presentation.model.GlobalSummaryModel

class SummaryListFragment : BaseFragment() {
    override val fragmentModule = SummaryListAndroidModule(this).module

    private val viewModel by instance<SummaryListViewModel>()
    private val globalSummaryAdapter by instance<GlobalSummaryAdapter>()
    private val countrySummaryAdapter by instance<CountryListSummaryAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_summary_list, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_summary_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
        viewModel.onCreate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuItemSort) {
            viewModel.onSortClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViews() {
        globalSummaryRecycler.layoutManager = LinearLayoutManager(requireContext())
        globalSummaryRecycler.adapter = globalSummaryAdapter

        countrySummaryRecycler.layoutManager = LinearLayoutManager(requireContext())
        countrySummaryRecycler.adapter = countrySummaryAdapter

        setupClickListeners()
    }

    private fun setupClickListeners() {
        errorStubLayout.findViewById<View>(R.id.errorStateRetryButton)
            .setOnClickListener(viewModel::onRetryClicked)
    }

    private fun observeViewModel() {
        viewModel.showProgressLiveData.observe(this, progressBar::show)
        viewModel.showErrorLiveData.observe(this, errorStubLayout::show)
        viewModel.globalSummaryLiveData.observe(this, this::displayGlobalSummary)
        viewModel.countriesSummaryLiveData.observe(this, this::displayCountriesSummary)
        viewModel.showOptionsDialogLiveData.observe(this, this::showOptionsDialog)
    }

    private fun showOptionsDialog(optionsDialogModel: DialogModel.OptionsDialogModel) {
        println("$optionsDialogModel")
    }

    private fun displayGlobalSummary(list: List<GlobalSummaryModel>) {
        globalSummaryAdapter.updateData(list)
    }

    private fun displayCountriesSummary(list: List<CountrySummaryListModel>) {
        countrySummaryAdapter.updateData(list)
    }
}