package org.michaellang.common.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.michaellang.common.base.BaseViewModelImpl

inline fun <reified VM : BaseViewModelImpl> AppCompatActivity.vm(
    factory: ViewModelProvider.Factory
): VM {
    return ViewModelProviders.of(this, factory)[VM::class.java]
}

inline fun <reified VM : BaseViewModelImpl> Fragment.vm(
    factory: ViewModelProvider.Factory
): VM {
    return ViewModelProviders.of(this, factory)[VM::class.java]
}

inline fun <VM : ViewModel> vmFactory(crossinline f: () -> VM): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
    }
}