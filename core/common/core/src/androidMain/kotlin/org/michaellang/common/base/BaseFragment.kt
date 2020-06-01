package org.michaellang.common.base

import android.content.Context
import androidx.fragment.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.closestKodein
import org.michaellang.common.di.CoroutineScopeCoreModule

abstract class BaseFragment : Fragment(), KodeinAware {
    private lateinit var _parentKodein: Kodein
    override val kodein: Kodein = Kodein.lazy {
        extend(_parentKodein)
        import(CoroutineScopeCoreModule().module)
        import(fragmentModule)
    }

    override val kodeinTrigger = KodeinTrigger()

    override fun onAttach(context: Context) {
        val parentKodein by closestKodein(context.applicationContext)
        _parentKodein = parentKodein
        kodeinTrigger.trigger()
        super.onAttach(context)
    }

    abstract val fragmentModule: Kodein.Module
}