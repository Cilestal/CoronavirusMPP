package org.michaellang.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.michaellang.common.di.CoroutineScopeCoreModule

abstract class BaseActivity : AppCompatActivity(), KodeinAware {
    private val _parentKodein by closestKodein()
    override val kodeinTrigger = KodeinTrigger()

    override val kodein: Kodein by retainedKodein {
        extend(_parentKodein)
        import(CoroutineScopeCoreModule().module)
        import(activityModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodeinTrigger.trigger()
    }

    abstract val activityModule: Kodein.Module

}