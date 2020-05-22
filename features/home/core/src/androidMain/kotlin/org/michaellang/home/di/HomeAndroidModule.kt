package org.michaellang.home.di

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import org.michaellang.common.di.KodeinModuleHolder
import org.michaellang.home.R

class HomeAndroidModule(
    private val activity: Activity
) : KodeinModuleHolder {
    override val module = Kodein.Module("home_android_module") {
        import(HomeCoreModule().module)

        bind<NavController>() with singleton {
            findNavController(activity, R.id.navHostFragment)
        }
    }
}