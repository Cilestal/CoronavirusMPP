package org.michaellang.summary.presentation.list

import org.michaellang.common.base.BaseViewModel
import org.michaellang.common.presentation.dialog.DialogModel
import org.michaellang.livedata.LiveData
import org.michaellang.livedata.SingleLiveEvent
import org.michaellang.summary.domain.model.CountrySummaryDomain
import org.michaellang.summary.domain.model.GlobalSummaryDomain
import org.michaellang.summary.presentation.model.CountrySummaryListModel
import org.michaellang.summary.presentation.model.GlobalSummaryModel

interface SummaryListViewModel : BaseViewModel {
    val globalSummaryLiveData: LiveData<List<GlobalSummaryModel>>
    val countriesSummaryLiveData: LiveData<List<CountrySummaryListModel>>

    val showErrorLiveData: LiveData<Boolean>
    val showProgressLiveData: LiveData<Boolean>
    val showOptionsDialogLiveData: LiveData<DialogModel.OptionsDialogModel>

    fun onRetryClicked()
    fun onSortClicked()
}