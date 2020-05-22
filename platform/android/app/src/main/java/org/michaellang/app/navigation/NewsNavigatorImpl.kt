package org.michaellang.app.navigation

import androidx.navigation.NavController
import org.michaellang.app.R
import org.michaellang.news.NewsNavigator

class NewsNavigatorImpl(
    private val navController: NavController
) : NewsNavigator {

    override fun goToNewsScreen() {
        navController.navigate(R.id.testAc3)
    }
}