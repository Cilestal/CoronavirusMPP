package org.michaellang.common.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

abstract class SimpleRecyclerViewAdapter<D, VH : SimpleRecyclerViewAdapter.SimpleViewHolder<D>>(
    context: Context
) : RecyclerView.Adapter<VH>() {

    protected val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    protected var data: List<D> = emptyList()
    var clickListener: ((D) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = layoutInflater.inflate(getLayoutRes(viewType), parent, false)
        return createViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val data = data[position]
        holder.bind(data)

        holder.itemView.setOnClickListener { clickListener?.invoke(data) }
    }

    abstract fun createViewHolder(view: View, viewType: Int): VH

    @LayoutRes
    abstract fun getLayoutRes(viewType: Int): Int

    override fun getItemCount(): Int = data.size

    open fun updateData(newData: Collection<D>) {
        val oldData = data
        data = ArrayList(newData)

        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldData[oldItemPosition] == data[newItemPosition]
            }

            override fun getOldListSize(): Int = oldData.size

            override fun getNewListSize(): Int = data.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldData[oldItemPosition] == data[newItemPosition]
            }

        }, true).dispatchUpdatesTo(this)
    }

    fun clear() {
        updateData(Collections.emptyList())
    }

    abstract class SimpleViewHolder<D>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(data: D)
    }

}