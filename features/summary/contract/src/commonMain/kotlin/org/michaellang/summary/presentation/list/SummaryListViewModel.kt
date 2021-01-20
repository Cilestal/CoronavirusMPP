package org.michaellang.summary.presentation.list

import org.michaellang.common.base.BaseViewModel
import org.michaellang.livedata.LiveData
import org.michaellang.summary.presentation.model.GlobalSummaryModel
import org.michaellang.summary.presentation.model.SummaryListModel
import org.michaellang.summary.presentation.model.SummaryListSortModel

interface SummaryListViewModel : BaseViewModel {
    val globalSummaryLiveData: LiveData<List<GlobalSummaryModel>>
    val countriesSummaryLiveData: LiveData<List<SummaryListModel>>

    val showGlobalSummary: LiveData<Boolean>
    val lastPageLiveData: LiveData<Boolean>
    val showErrorLiveData: LiveData<Boolean>
    val showProgressLiveData: LiveData<Boolean>
    val showOptionsDialogLiveData: LiveData<List<SummaryListSortModel>>

    fun onRetryClicked()
    fun onSortClicked()
    fun loadMore()
    fun onSortItemSelected(model: SummaryListSortModel)
    fun onQueryTextChange(text: String?)
    fun onSearchOpened()
    fun onSearchClosed()
}