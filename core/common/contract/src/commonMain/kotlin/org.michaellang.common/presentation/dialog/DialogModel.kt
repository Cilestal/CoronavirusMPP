package org.michaellang.common.presentation.dialog

sealed class DialogModel {

    data class OptionsDialogModel(
        val options: List<OptionModel>
    ) : DialogModel()
}

data class OptionModel(
    val option: String,
    val selected: Boolean
)