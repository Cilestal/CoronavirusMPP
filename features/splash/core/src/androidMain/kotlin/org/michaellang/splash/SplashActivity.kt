package org.michaellang.splash

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_splash.*
import org.kodein.di.erased.instance
import org.michaellang.common.base.BaseActivity
import org.michaellang.common.extensions.onComplete

class SplashActivity : BaseActivity() {
    override val activityModule = SplashAndroidModule(this).module
    private val viewModel by instance<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        observeViewModel()

        viewModel.onCreate()
    }

    private fun observeViewModel() {
        viewModel.startAnimationLiveData.observe(this, ::startAnimation)
    }

    private fun startAnimation(duration: Long) {
        splashTitle.visibility = View.VISIBLE
        AlphaAnimation(0f, 1f).apply {
            onComplete(viewModel::onAnimationEnd)
            this.duration = duration
            splashTitle.animation = this
        }
    }

}