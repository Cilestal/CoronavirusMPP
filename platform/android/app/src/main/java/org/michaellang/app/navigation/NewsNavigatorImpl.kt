package org.michaellang.app.navigation

import androidx.fragment.app.FragmentManager
import org.michaellang.common.extensions.navigateToItem
import org.michaellang.home.HomeBottomMenuState
import org.michaellang.news.NewsNavigator

class NewsNavigatorImpl(
    private val fragmentManager: FragmentManager
) : NewsNavigator {

    override fun goToNewsScreen() {
        navigateToItem(
            fragmentManager,
            HomeBottomMenuState.values().size,
            HomeBottomMenuState.NEWS.index
        )
    }
}