package org.michaellang.common.view

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class EndlessRecyclerOnScrollListener(
    private val onLoadMoreDelegate: () -> Unit
) : RecyclerView.OnScrollListener() {

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = LOAD_MORE_VISIBLE_THRESHOLD

    // True if we are still waiting for the last set of data to load.
    private var loading = true

    private var lastQueryTime = 0L

    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    constructor(
        layoutManager: LinearLayoutManager,
        onLoadMoreDelegate: () -> Unit
    ) : this(onLoadMoreDelegate) {
        this.mLayoutManager = layoutManager
    }

    constructor(
        layoutManager: GridLayoutManager,
        onLoadMoreDelegate: () -> Unit
    ) : this(onLoadMoreDelegate) {
        this.mLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount
    }

    constructor(
        layoutManager: StaggeredGridLayoutManager,
        onLoadMoreDelegate: () -> Unit
    ) : this(onLoadMoreDelegate) {
        this.mLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = mLayoutManager.itemCount

        when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                // get maximum element within the list
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> {
                lastVisibleItemPosition =
                    (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                lastVisibleItemPosition =
                    (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            }
            else                          -> {
                return
            }
        }

        // max 1 query per second
        if (System.currentTimeMillis() - lastQueryTime <= LOAD_MORE_FREQUENCY_IN_MILLS) {
            return
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount
            && view.adapter!!.itemCount > visibleThreshold
        ) {// This condition will useful when recyclerview has less than visibleThreshold items
            lastQueryTime = System.currentTimeMillis()
            onLoadMoreDelegate()
            loading = true
        }
    }

    fun updateState(loading: Boolean) {
        this.loading = loading
        if (!loading) {
            lastQueryTime = 0L
        }
    }

    companion object {
        private const val LOAD_MORE_VISIBLE_THRESHOLD = 2
        private const val LOAD_MORE_FREQUENCY_IN_MILLS = 1_000L
    }
}