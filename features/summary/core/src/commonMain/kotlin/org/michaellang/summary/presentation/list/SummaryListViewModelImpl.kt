package org.michaellang.summary.presentation.list

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.michaellang.common.MR
import org.michaellang.common.base.BaseViewModelImpl
import org.michaellang.common.coroutines.CommonDispatchers
import org.michaellang.common.model.Page
import org.michaellang.common.provider.ResourceProvider
import org.michaellang.livedata.MutableLiveData
import org.michaellang.livedata.SingleLiveEvent
import org.michaellang.summary.domain.interactor.SummaryInteractor
import org.michaellang.summary.domain.interactor.SummaryListSortInteractor
import org.michaellang.summary.domain.model.SummaryListSortDomain
import org.michaellang.summary.presentation.mapper.CountrySummaryListModelMapper
import org.michaellang.summary.presentation.mapper.GlobalSummaryModelMapper
import org.michaellang.summary.presentation.mapper.SummaryListSortModelMapper
import org.michaellang.summary.presentation.model.CountrySummaryListModel
import org.michaellang.summary.presentation.model.GlobalSummaryModel
import org.michaellang.summary.presentation.model.SummaryListHeader
import org.michaellang.summary.presentation.model.SummaryListModel
import org.michaellang.summary.presentation.model.SummaryListSortModel

class SummaryListViewModelImpl(
    private val summaryInteractor: SummaryInteractor,
    private val summaryListSortInteractor: SummaryListSortInteractor,
    private val globalSummaryMapper: GlobalSummaryModelMapper,
    private val countrySummaryListModelMapper: CountrySummaryListModelMapper,
    private val summaryListSortModelMapper: SummaryListSortModelMapper,
    private val res: ResourceProvider,
    coroutineScope: CoroutineScope
) : BaseViewModelImpl(coroutineScope),
    SummaryListViewModel, CoroutineScope by coroutineScope {

    private var initialized: Boolean = false

    override val globalSummaryLiveData = MutableLiveData<List<GlobalSummaryModel>>()
    override val countriesSummaryLiveData = MutableLiveData<List<SummaryListModel>>()

    override val showGlobalSummary = MutableLiveData<Boolean>()
    override val lastPageLiveData = MutableLiveData<Boolean>()
    override val showErrorLiveData = MutableLiveData<Boolean>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val showOptionsDialogLiveData = SingleLiveEvent<List<SummaryListSortModel>>()

    private lateinit var summary: List<SummaryListModel>
    private lateinit var currentPage: Page
    private var loadDataJob: Job? = null
    private var searchQueryJob: Job? = null

    private var sortOptions: List<SummaryListSortModel> = emptyList()
    private val currentSort get() = sortOptions.first { it.selected }.sort

    override fun onCreate() {
        launch {
            if (!initialized) {
                loadFilters()
                reloadData()
            }
        }
    }

    override fun onRetryClicked() {
        reloadData()
    }

    override fun loadMore() {
        if (showProgressLiveData.getValue() == true || currentPage.lastPage) return

        showProgressLiveData.postValue(true)

        var end = currentPage.end
        if (currentPage.end >= summary.size) {
            lastPageLiveData.postValue(true)
            currentPage = currentPage.copy(lastPage = true)
            end = summary.size
        }

        if (currentPage.start <= end) {
            countriesSummaryLiveData.postValue(summary.subList(0, end))
        }

        showProgressLiveData.postValue(false)
        currentPage = currentPage.copy(page = currentPage.page + 1)
    }

    private fun reloadData() {
        this.loadDataJob?.cancel()
        this.loadDataJob = launch(CommonDispatchers.Default) {
            try {
                showErrorLiveData.postValue(false)
                showProgressLiveData.postValue(true)

                loadGlobalSummary()
                loadCountriesSummary()

                showProgressLiveData.postValue(false)
                initialized = true
            } catch (thr: Throwable) {
                handleError(thr)
            }
        }
    }

    private suspend fun loadCountriesSummary() {
        this.currentPage = Page()
        this.lastPageLiveData.postValue(false)

        val header = SummaryListHeader(res.getString(MR.strings.country_summary_header_title))

        summary = listOf(header) + summaryInteractor.getCountriesSummaryData()
            .map(countrySummaryListModelMapper::map)
            .sortedByDescending(sortSelector)

        loadMore()
    }

    private suspend fun loadGlobalSummary() {
        val summaryModelList = summaryInteractor.getGlobalSummary()
            .let(globalSummaryMapper::map)
        globalSummaryLiveData.postValue(summaryModelList)
    }

    private fun handleError(thr: Throwable) {
        showErrorLiveData.postValue(true)
        showProgressLiveData.postValue(false)
    }

    // sort
    override fun onSortItemSelected(model: SummaryListSortModel) {
        summaryListSortInteractor.currentSortType = model.sort

        sortOptions = updateSortOptions(model.sort, sortOptions)
        reloadData()
    }

    override fun onSortClicked() {
        sortOptions.takeIf(Collection<SummaryListSortModel>::isNotEmpty)
            ?.let(showOptionsDialogLiveData::postValue)
    }

    override fun onQueryTextChange(text: String?) {
        this.searchQueryJob?.cancel()
        this.searchQueryJob = launch {
            if (showErrorLiveData.getValue() == true) return@launch

            val data = if (text.isNullOrEmpty()) {
                val end = if (currentPage.end >= summary.size) summary.size else currentPage.end
                summary.subList(0, end)
            } else {
                summary.filter {
                    if (it !is CountrySummaryListModel) true
                    else it.country.contains(text, true)
                }
            }

            countriesSummaryLiveData.postValue(data)
        }
    }

    override fun onSearchClosed() {
        showGlobalSummary.postValue(true)
        loadMore()
    }

    override fun onSearchOpened() {
        showGlobalSummary.postValue(false)
        lastPageLiveData.postValue(true)
    }

    private suspend fun loadFilters() {
        withContext(CommonDispatchers.Default) {
            val currentSort = summaryListSortInteractor.currentSortType
            sortOptions = summaryListSortInteractor.getAvailableSummaryListSort()
                .map(summaryListSortModelMapper::map)
                .let { updateSortOptions(currentSort, it) }
        }
    }

    private fun updateSortOptions(
        current: SummaryListSortDomain,
        sortOptions: List<SummaryListSortModel>
    ): List<SummaryListSortModel> {
        var list = sortOptions
            .map { it.copy(selected = it.sort == current) }

        if (list.none { it.selected }) {
            list = sortOptions.toMutableList()
                .also { it[0] = sortOptions.first().copy(selected = true) }
        }

        return list
    }

    private val sortSelector: (CountrySummaryListModel) -> Long = {
        when (currentSort) {
            SummaryListSortDomain.TOTAL_CONFIRMED -> it.totalConfirmed
            SummaryListSortDomain.TOTAL_DEATHS -> it.totalDeaths
            SummaryListSortDomain.TOTAL_RECOVERED -> it.totalRecovered
        }
    }
}