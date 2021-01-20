package org.michaellang.summary.presentation.list

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.RequestManager
import org.michaellang.common.view.LoadMoreAdapter
import org.michaellang.common.view.SimpleRecyclerViewAdapter
import org.michaellang.summary.R
import org.michaellang.summary.databinding.ItemCountrySummaryBinding
import org.michaellang.summary.databinding.ItemSummaryHeaderBinding
import org.michaellang.summary.presentation.model.CountrySummaryListModel
import org.michaellang.summary.presentation.model.SummaryListHeader
import org.michaellang.summary.presentation.model.SummaryListModel

class CountryListSummaryAdapter(
    context: Context,
    private val glide: RequestManager
) : LoadMoreAdapter<SummaryListModel, CountryListSummaryAdapterBaseVH>(context) {

    override fun createItemViewHolder(view: View, viewType: Int): CountryListSummaryAdapterBaseVH {
        return if (viewType == VIEW_TYPE_HEADER) {
            CountrySummaryHeaderViewHolder(view)
        } else {
            CountrySummaryViewHolder(view, glide)
        }
    }

    override fun getItemLayoutRes(viewType: Int): Int {
        return if (viewType == VIEW_TYPE_HEADER) {
            R.layout.item_summary_header
        } else {
            R.layout.item_country_summary
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position).takeIf { it > 0 }
            ?: if (data[position] is SummaryListHeader) {
                VIEW_TYPE_HEADER
            } else {
                VIEW_TYPE_ITEM
            }
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 0x463
        private const val VIEW_TYPE_ITEM = 0x464
    }
}

abstract class CountryListSummaryAdapterBaseVH(
    itemView: View
) : SimpleRecyclerViewAdapter.SimpleViewHolder<SummaryListModel>(itemView)

class CountrySummaryViewHolder(
    itemView: View,
    private val glide: RequestManager
) : CountryListSummaryAdapterBaseVH(itemView) {

    private val binding: ItemCountrySummaryBinding = DataBindingUtil.bind(itemView)!!

    override fun bind(data: SummaryListModel) {
        check(data is CountrySummaryListModel)

        glide.load(data.countryFlagUrl)
            .skipMemoryCache(true)
            .into(binding.countryFlagImage)

        binding.countryName.text = data.country

        binding.totalConfirmedText.text = data.totalConfirmed.toString()
        binding.totalDeathsText.text = data.totalDeaths.toString()
        binding.totalRecoveredText.text = data.totalRecovered.toString()
    }
}

class CountrySummaryHeaderViewHolder(
    itemView: View
) : CountryListSummaryAdapterBaseVH(itemView) {

    private val binding: ItemSummaryHeaderBinding = DataBindingUtil.bind(itemView)!!

    override fun bind(data: SummaryListModel) {
        check(data is SummaryListHeader)
        binding.title.text = data.title
    }
}
