package org.michaellang.common.extensions

import android.view.animation.Animation

inline fun Animation.onComplete(crossinline onEnd: () -> Unit) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(p0: Animation?) {
            // empty
        }

        override fun onAnimationEnd(p0: Animation?) {
            onEnd()
        }

        override fun onAnimationStart(p0: Animation?) {
            // empty
        }

    })
}