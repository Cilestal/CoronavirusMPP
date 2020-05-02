package org.michaellang.mviproject.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class TestActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {

    }

}