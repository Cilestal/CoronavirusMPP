package org.michaellang.summary.presentation.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import org.kodein.di.instance
import org.michaellang.common.base.BaseFragment
import org.michaellang.common.extensions.setOnClickListener
import org.michaellang.common.extensions.show
import org.michaellang.common.view.EndlessRecyclerOnScrollListener
import org.michaellang.summary.R
import org.michaellang.summary.databinding.FragmentSummaryListBinding
import org.michaellang.summary.di.SummaryListAndroidModule
import org.michaellang.summary.presentation.model.GlobalSummaryModel
import org.michaellang.summary.presentation.model.SummaryListModel
import org.michaellang.summary.presentation.model.SummaryListSortModel

class SummaryListFragment : BaseFragment<FragmentSummaryListBinding>(), MenuItem.OnActionExpandListener,
    SearchView.OnQueryTextListener {
    override val module = SummaryListAndroidModule(this).module

    private val viewModel by instance<SummaryListViewModel>()
    private val globalSummaryAdapter by instance<GlobalSummaryAdapter>()
    private val countrySummaryAdapter by instance<CountryListSummaryAdapter>()
    private val mergeAdapter by instance<MergeAdapter>()

    private lateinit var scrollListener: EndlessRecyclerOnScrollListener

    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_summary_list, menu)

        with(menu.findItem(R.id.menuItemSearch)) {
            setOnActionExpandListener(this@SummaryListFragment)
            (this.actionView as? SearchView)?.setOnQueryTextListener(this@SummaryListFragment)
        }
    }

    override fun onMenuItemActionExpand(item: MenuItem): Boolean {
        viewModel.onSearchOpened()

        this.scrollListener.updateState(true)
        binding.swipeRefreshLayout.isRefreshing = false
        binding.swipeRefreshLayout.isEnabled = false
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
        viewModel.onSearchClosed()

        this.scrollListener.updateState(false)
        binding.swipeRefreshLayout.isRefreshing = false
        binding.swipeRefreshLayout.isEnabled = true
        return true
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
        binding.swipeRefreshLayout.setOnRefreshListener(viewModel::onRetryClicked)
        mergeAdapter.addAdapter(globalSummaryAdapter)
        mergeAdapter.addAdapter(countrySummaryAdapter)

        val layoutManager = LinearLayoutManager(context)
        binding.contentRecyclerView.layoutManager = layoutManager
        binding.contentRecyclerView.adapter = mergeAdapter

        this.scrollListener = EndlessRecyclerOnScrollListener(layoutManager, viewModel::loadMore)
        binding.contentRecyclerView.addOnScrollListener(scrollListener)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.errorStubLayout.findViewById<View>(R.id.errorStateRetryButton)
            .setOnClickListener(viewModel::onRetryClicked)
    }

    private fun observeViewModel() {
        viewModel.showProgressLiveData.observe(this, ::showProgress)
        viewModel.showErrorLiveData.observe(this, binding.errorStubLayout::show)
        viewModel.globalSummaryLiveData.observe(this, this::displayGlobalSummary)
        viewModel.countriesSummaryLiveData.observe(this, this::displayCountriesSummary)
        viewModel.showOptionsDialogLiveData.observe(this, this::showOptionsDialog)
        viewModel.lastPageLiveData.observe(this, this::updateLastPage)
        viewModel.showGlobalSummary.observe(this, this::showGlobalSummary)
    }

    private fun showGlobalSummary(show: Boolean) {
        if (show) {
            mergeAdapter.addAdapter(0, globalSummaryAdapter)
        } else {
            mergeAdapter.removeAdapter(globalSummaryAdapter)
        }
    }

    private fun updateLastPage(lastPage: Boolean) {
        countrySummaryAdapter.lastPage = lastPage
    }

    private fun showProgress(show: Boolean) {
        binding.progressBar.show(show)
        binding.swipeRefreshLayout.isRefreshing = show
        scrollListener.updateState(show)
    }

    private fun showOptionsDialog(options: List<SummaryListSortModel>) {
        this.alertDialog?.cancel()

        val titles = options.map { it.title }.toTypedArray()
        val checkedItem = options.indexOfFirst { it.selected }

        this.alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.summary_list_menu_sort_title)
            .setSingleChoiceItems(titles, checkedItem, null)
            .setPositiveButton(R.string.button_apply) { dialog, _ ->
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                viewModel.onSortItemSelected(options[selectedPosition])
            }
            .setNegativeButton(R.string.button_cancel, null)
            .create()
            .also(AlertDialog::show)

    }

    private fun displayGlobalSummary(list: List<GlobalSummaryModel>) {
        globalSummaryAdapter.updateData(list)
    }

    private fun displayCountriesSummary(list: List<SummaryListModel>) {
        countrySummaryAdapter.updateData(list)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.onQueryTextChange(newText)
        return true
    }

    override fun layoutRes() = R.layout.fragment_summary_list
}