package org.michaellang.summary.presentation.list

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.michaellang.common.MR
import org.michaellang.common.base.BaseViewModelImpl
import org.michaellang.common.coroutines.CommonDispatchers
import org.michaellang.common.presentation.dialog.DialogModel
import org.michaellang.common.provider.ResourceProvider
import org.michaellang.livedata.MutableLiveData
import org.michaellang.livedata.SingleLiveEvent
import org.michaellang.summary.domain.interactor.SummaryInteractor
import org.michaellang.summary.domain.model.CountrySummaryDomain
import org.michaellang.summary.domain.model.GlobalSummaryDomain
import org.michaellang.summary.presentation.mapper.CountrySummaryListModelMapper
import org.michaellang.summary.presentation.mapper.GlobalSummaryModelMapper
import org.michaellang.summary.presentation.model.CountrySummaryListModel
import org.michaellang.summary.presentation.model.GlobalSummaryModel

class SummaryListViewModelImpl(
    private val summaryInteractor: SummaryInteractor,
    private val globalSummaryMapper: GlobalSummaryModelMapper,
    private val countrySummaryListModelMapper: CountrySummaryListModelMapper,
    private val resourceProvider: ResourceProvider,
    coroutineScope: CoroutineScope
) : BaseViewModelImpl(coroutineScope),
    SummaryListViewModel, CoroutineScope by coroutineScope {

    private var initialized: Boolean = false

    override val globalSummaryLiveData = MutableLiveData<List<GlobalSummaryModel>>()
    override val countriesSummaryLiveData = MutableLiveData<List<CountrySummaryListModel>>()

    override val showErrorLiveData = MutableLiveData<Boolean>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val showOptionsDialogLiveData = SingleLiveEvent<DialogModel.OptionsDialogModel>()

    private var loadDataJob: Job? = null

    override fun onCreate() {
        if (!initialized) {
            loadData()
        }
    }

    override fun onRetryClicked() {
        loadData()
    }

    override fun onSortClicked() {
        //todo
//        val dialogOptions = listOf(
//            resourceProvider.getString(MR.strings.menu_sort_total_death),
//            resourceProvider.getString(MR.strings.menu_sort_total_confirmed),
//            resourceProvider.getString(MR.strings.menu_sort_total_recovered)
//        )
//        showOptionsDialogLiveData.postValue(
//            DialogModel.OptionsDialogModel(dialogOptions)
//        )
    }

    private fun loadData() {
        this.loadDataJob?.cancel()
        this.loadDataJob = launch(CommonDispatchers.Default) {
            try {
                showErrorLiveData.postValue(false)
                showProgressLiveData.postValue(true)

                val global = summaryInteractor.getGlobalSummary()
                val countriesSummary = summaryInteractor.getCountriesSummaryData()

                displayData(global, countriesSummary)

                initialized = true
            } catch (thr: Throwable) {
                handleError(thr)
            }
        }
    }

    private fun handleError(thr: Throwable) {
        showErrorLiveData.postValue(true)
        showProgressLiveData.postValue(false)
    }

    private fun displayData(
        global: GlobalSummaryDomain,
        countriesSummary: List<CountrySummaryDomain>
    ) {
        showProgressLiveData.postValue(false)

        val summaryModelList = globalSummaryMapper.map(global)
        val countryList = countriesSummary.map(countrySummaryListModelMapper::map)

        globalSummaryLiveData.postValue(summaryModelList)
        countriesSummaryLiveData.postValue(countryList)
    }
}