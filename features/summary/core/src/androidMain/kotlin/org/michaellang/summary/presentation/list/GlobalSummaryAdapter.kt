package org.michaellang.summary.presentation.list

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.item_global_summary.view.*
import org.michaellang.common.view.SimpleRecyclerViewAdapter
import org.michaellang.summary.R
import org.michaellang.summary.presentation.model.GlobalSummaryModel

class GlobalSummaryAdapter(
    context: Context
) : SimpleRecyclerViewAdapter<GlobalSummaryModel, GlobalSummaryViewHolder>(context) {

    override fun createViewHolder(view: View, viewType: Int) = GlobalSummaryViewHolder(view)

    override fun getLayoutRes(viewType: Int) = R.layout.item_global_summary
}

class GlobalSummaryViewHolder(itemView: View) :
    SimpleRecyclerViewAdapter.SimpleViewHolder<GlobalSummaryModel>(itemView) {

    override fun bind(data: GlobalSummaryModel) {
        itemView.globalSummaryItemLabel.text = data.title
        itemView.globalSummaryItemText.text = data.value.toString()
    }

}