package org.michaellang.app.navigation

import androidx.navigation.NavController
import org.michaellang.app.R
import org.michaellang.summary.SummaryNavigator

class SummaryNavigatorImpl(
    private val navController: NavController
) : SummaryNavigator {

    override fun goToSummaryScreen() {
        navController.navigate(R.id.testAc2)
    }
}