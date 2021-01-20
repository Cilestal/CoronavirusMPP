package org.michaellang.common.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.display(display: Boolean) {
    this.visibility = if (display) View.VISIBLE else View.INVISIBLE
}

fun View.setOnClickListener(listener: () -> Unit) {
    this.setOnClickListener {
        listener()
    }
}

fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

