package org.michaellang.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import org.kodein.di.Kodein
import org.kodein.di.erased.instance
import org.michaellang.common.base.BaseActivity
import org.michaellang.home.Const.TAG_HOME_NAV_GRAPH
import org.michaellang.home.di.HomeAndroidModule

class HomeActivity : BaseActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    override val activityModule: Kodein.Module = HomeAndroidModule(this).module

    private val navigationRes by instance<@NavigationRes Int>(TAG_HOME_NAV_GRAPH)
    private val viewModel by instance<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupViews()
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
            HomeBottomMenuState.SUMMARY -> R.id.navigation_summary_list
            HomeBottomMenuState.NEWS -> R.id.navigation_news
        }
        bottomNavigationView.menu.findItem(id).isChecked = true
    }

    private fun setupViews() {
        //bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    private fun setupNav() {
        setSupportActionBar(toolbar)

        with(findNavController(R.id.navHostFragment)) {
            graph = navInflater.inflate(navigationRes)

            val appBarConfiguration = AppBarConfiguration(bottomNavigationView.menu)
            setupActionBarWithNavController(this, appBarConfiguration)
            bottomNavigationView.setupWithNavController(this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.navigation_summary_list -> viewModel.onSummaryTabClicked()
            R.id.navigation_news -> viewModel.onNewsTabClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem) = onOptionsItemSelected(item)

    companion object {
        @JvmStatic
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}