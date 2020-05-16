package org.michaellang.common.base

import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.michaellang.common.di.CoroutineScopeCoreModule

abstract class BaseActivity : AppCompatActivity(), KodeinAware {
    private val _parentKodein by closestKodein()
    override val kodein: Kodein by retainedKodein {
        extend(_parentKodein)
        import(CoroutineScopeCoreModule().module)
        import(activityModule)
    }

    abstract val activityModule: Kodein.Module

}