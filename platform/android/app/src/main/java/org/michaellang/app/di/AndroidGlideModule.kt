package org.michaellang.app.di

import android.app.Activity
import android.content.Context
import android.view.View
import com.bumptech.glide.RequestManager
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.multiton
import org.michaellang.app.GlideApp

class AndroidGlideModule {
    val module = DI.Module("android_glide_module") {
        bind<RequestManager>() with multiton { context: Context ->
            GlideApp.with(context)
        }

        bind<RequestManager>() with multiton { view: View ->
            GlideApp.with(view)
        }

        bind<RequestManager>() with multiton { activity: Activity ->
            GlideApp.with(activity)
        }
    }
}