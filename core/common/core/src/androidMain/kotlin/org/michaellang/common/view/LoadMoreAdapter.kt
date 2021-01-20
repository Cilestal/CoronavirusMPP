package org.michaellang.common.view

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import org.michaellang.common.R

abstract class LoadMoreAdapter<D, VH : SimpleRecyclerViewAdapter.SimpleViewHolder<D>>(
    context: Context
) : SimpleRecyclerViewAdapter<D, SimpleRecyclerViewAdapter.SimpleViewHolder<D>>(context) {

    var lastPage = false
        set(value) {
            if (field != value) {
                if (value) {
                    notifyItemRemoved(progressPosition)
                } else {
                    notifyItemInserted(progressPosition)
                }
                field = value
            }
        }

    final override fun createViewHolder(view: View, viewType: Int): SimpleViewHolder<D> {
        return if (viewType == VIEW_TYPE_PROGRESS_BAR) {
            ProgressBarViewHolder(view)
        } else {
            createItemViewHolder(view, viewType)
        }
    }

    final override fun onBindViewHolder(holder: SimpleViewHolder<D>, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_PROGRESS_BAR) {
            return
        }

        onBindItemViewHolder(holder as VH, position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position).takeIf { it > 0 }
            ?: if (progressPosition == position) {
                VIEW_TYPE_PROGRESS_BAR
            } else {
                super.getItemViewType(position)
            }
    }

    final override fun getLayoutRes(viewType: Int): Int {
        return if (viewType == VIEW_TYPE_PROGRESS_BAR) {
            R.layout.item_progress_bar
        } else {
            getItemLayoutRes(viewType)
        }
    }

    private val progressPosition get() = if (!lastPage) itemCount - 1 else -1

    override fun getItemCount() = if (!lastPage) data.size + 1 else data.size

    fun clearData() {
        lastPage = false
        updateData(emptyList())
    }

    abstract fun getItemLayoutRes(viewType: Int): Int
    abstract fun createItemViewHolder(view: View, viewType: Int): VH

    override fun updateData(newData: Collection<D>) {
        val oldItemCount = itemCount
        val oldData = data

        data = ArrayList(newData)

        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldData.getOrNull(oldItemPosition) == data.getOrNull(newItemPosition)
            }

            override fun getOldListSize(): Int = oldItemCount

            override fun getNewListSize(): Int = itemCount

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldData.getOrNull(oldItemPosition) == data.getOrNull(newItemPosition)
            }

        }, true).dispatchUpdatesTo(this)
    }

    open fun onBindItemViewHolder(vh: VH, position: Int) {
        super.onBindViewHolder(vh, position)
    }

    companion object {
        const val VIEW_TYPE_PROGRESS_BAR = 0x353
    }
}

class ProgressBarViewHolder<D>(
    itemView: View
) : SimpleRecyclerViewAdapter.SimpleViewHolder<D>(itemView) {

    override fun bind(data: D) {
        //empty
    }
}