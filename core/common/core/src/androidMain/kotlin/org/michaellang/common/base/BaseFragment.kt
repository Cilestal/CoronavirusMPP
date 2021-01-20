package org.michaellang.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DITrigger
import org.kodein.di.android.closestDI
import org.michaellang.common.di.CoroutineScopeCoreModule

abstract class BaseFragment<B : ViewDataBinding> : Fragment(), DIAware {
    private lateinit var _parentDI: DI
    override val di: DI = DI.lazy {
        extend(_parentDI)
        import(CoroutineScopeCoreModule().module)
        import(fragmentModule)
    }
    protected lateinit var binding: B

    override val diTrigger = DITrigger()

    override fun onAttach(context: Context) {
        val parentDI by closestDI(context.applicationContext)
        _parentDI = parentDI
        diTrigger.trigger()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutRes(), container, false)
        return binding.root
    }

    @LayoutRes
    abstract fun layoutRes(): Int

    abstract val fragmentModule: DI.Module
}