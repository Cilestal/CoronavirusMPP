package org.michaellang.app.navigation

import android.content.Context
import android.content.Intent
import org.michaellang.home.HomeActivity
import org.michaellang.home.HomeNavigator

class HomeNavigatorImpl(
    private val context: Context
) : HomeNavigator {

    override fun goToHomeScreen() {
        val intent = HomeActivity.newIntent(context)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

}