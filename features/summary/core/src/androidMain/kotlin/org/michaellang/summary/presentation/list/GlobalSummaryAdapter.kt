package org.michaellang.summary.presentation.list

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import org.michaellang.common.view.SimpleRecyclerViewAdapter
import org.michaellang.summary.R
import org.michaellang.summary.databinding.ItemGlobalSummaryBinding
import org.michaellang.summary.presentation.model.GlobalSummaryModel

class GlobalSummaryAdapter(
    context: Context
) : SimpleRecyclerViewAdapter<GlobalSummaryModel, GlobalSummaryViewHolder>(context) {

    override fun createViewHolder(view: View, viewType: Int) = GlobalSummaryViewHolder(view)

    override fun getLayoutRes(viewType: Int) = R.layout.item_global_summary

    override fun updateData(newData: Collection<GlobalSummaryModel>) {
        this.data = ArrayList(newData)
        notifyDataSetChanged()
    }
}

class GlobalSummaryViewHolder(itemView: View) :
    SimpleRecyclerViewAdapter.SimpleViewHolder<GlobalSummaryModel>(itemView) {

    private val binding: ItemGlobalSummaryBinding = DataBindingUtil.bind(itemView)!!

    override fun bind(data: GlobalSummaryModel) {
        binding.globalSummaryItemLabel.text = data.title
        binding.globalSummaryItemText.text = data.value.toString()
    }

}