package org.michaellang.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DITrigger
import org.kodein.di.android.closestDI
import org.kodein.di.android.retainedDI
import org.michaellang.common.di.CoroutineScopeCoreModule
import org.michaellang.common.di.KodeinModuleHolder

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(), DIAware, KodeinModuleHolder {
    private val _parentDI by closestDI()
    override val diTrigger = DITrigger()

    protected lateinit var binding: B

    override val di: DI by retainedDI {
        extend(_parentDI)
        import(CoroutineScopeCoreModule().module)
        import(module)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diTrigger.trigger()

        binding = DataBindingUtil.setContentView(this, layoutRes())
    }

    @LayoutRes
    abstract fun layoutRes(): Int
}