package org.michaellang.splash

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import org.kodein.di.instance
import org.michaellang.common.base.BaseActivity
import org.michaellang.common.extensions.onComplete
import org.michaellang.splash.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val module = SplashAndroidModule(this).module
    private val viewModel by instance<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
        viewModel.onCreate()
    }

    private fun observeViewModel() {
        viewModel.startAnimationLiveData.observe(this, ::startAnimation)
    }

    private fun startAnimation(duration: Long) {
        binding.splashTitle.visibility = View.VISIBLE
        AlphaAnimation(0f, 1f).apply {
            onComplete(viewModel::onAnimationEnd)
            this.duration = duration
            binding.splashTitle.animation = this
        }
    }

    override fun layoutRes() = R.layout.activity_splash

}