package org.michaellang.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.lifecycle.observe
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_home.*
import org.kodein.di.Kodein
import org.kodein.di.erased.instance
import org.michaellang.common.base.BaseActivity
import org.michaellang.common.extensions.setupWithNavController
import org.michaellang.home.Const.TAG_HOME_NAV_GRAPH
import org.michaellang.home.di.HomeAndroidModule

class HomeActivity : BaseActivity() {
    override val activityModule: Kodein.Module = HomeAndroidModule(this).module

    private val navItems by instance<@NavigationRes List<Int>>(TAG_HOME_NAV_GRAPH)

    private val viewModel by instance<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            setupNav()
        }
        observeViewModel()

        viewModel.onCreate()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupNav()
    }

    private fun observeViewModel() {
        viewModel.bottomMenuStateLiveData.observe(this, ::selectBottomMenuItem)
    }

    private fun selectBottomMenuItem(it: HomeBottomMenuState) {
        @IdRes val id = when (it) {
            HomeBottomMenuState.SUMMARY -> R.id.summary_navigation
            HomeBottomMenuState.NEWS -> R.id.news_navigation
        }
        bottomNavigationView.menu.findItem(id).isChecked = true
    }

    private fun setupNav() {
        setSupportActionBar(toolbar)

        bottomNavigationView.setupWithNavController(
            navGraphIds = navItems,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainer,
            intent = intent,
            listener = ::onOptionsItemSelected
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.summary_navigation -> viewModel.onSummaryTabClicked()
            R.id.news_navigation -> viewModel.onNewsTabClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmStatic
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}