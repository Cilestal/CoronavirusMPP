package org.michaellang.summary.presentation.list

import android.content.Context
import android.view.View
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_country_summary.view.*
import org.michaellang.common.extensions.show
import org.michaellang.common.view.SimpleRecyclerViewAdapter
import org.michaellang.summary.R
import org.michaellang.summary.presentation.model.CountrySummaryListModel

class CountryListSummaryAdapter(
    context: Context,
    private val glide: RequestManager
) : SimpleRecyclerViewAdapter<CountrySummaryListModel, CountrySummaryViewHolder>(context) {

    override fun createViewHolder(view: View, viewType: Int) = CountrySummaryViewHolder(view, glide)

    override fun getLayoutRes(viewType: Int) = R.layout.item_country_summary

    override fun onBindViewHolder(holder: CountrySummaryViewHolder, position: Int) {
        holder.lastItem = position == data.size - 1
        super.onBindViewHolder(holder, position)
    }
}

class CountrySummaryViewHolder(
    itemView: View,
    private val glide: RequestManager
) : SimpleRecyclerViewAdapter.SimpleViewHolder<CountrySummaryListModel>(itemView) {

    var lastItem: Boolean = false

    override fun bind(data: CountrySummaryListModel) {
        glide.load(data.countryFlagUrl)
            .skipMemoryCache(true)
            .into(itemView.countryFlagImage)

        itemView.countryName.text = data.country

        itemView.totalConfirmedText.text = data.totalConfirmed.toString()
        itemView.totalDeathsText.text = data.totalDeaths.toString()
        itemView.totalRecoveredText.text = data.totalRecovered.toString()

        itemView.separator.show(!lastItem)
    }

}