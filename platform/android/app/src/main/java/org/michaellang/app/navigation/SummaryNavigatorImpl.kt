package org.michaellang.app.navigation

import androidx.fragment.app.FragmentManager
import org.michaellang.common.extensions.navigateToItem
import org.michaellang.home.HomeBottomMenuState
import org.michaellang.summary.presentation.SummaryNavigator

class SummaryNavigatorImpl(
    private val fragmentManager: FragmentManager
) : SummaryNavigator {

    override fun goToSummaryScreen() {
        navigateToItem(
            fragmentManager,
            HomeBottomMenuState.values().size,
            HomeBottomMenuState.SUMMARY.index
        )
    }
}