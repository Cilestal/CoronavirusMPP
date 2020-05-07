package org.michaellang.mviproject.test

import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.erased.instance
import org.michaellang.common.base.BaseActivity
import org.michaellang.data.repository.CovidRepository

class TestActivity : BaseActivity() {
    override val activityModule = Kodein.Module("test") {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rep by instance<CovidRepository>()
        println(rep)

        CoroutineScope(Dispatchers.Default).launch {
            try {
                val countryList = rep.getCountryList()

                val countrySummaryData = rep.getCountrySummaryData("Ukraine")

                val globalSummary = rep.getGlobalSummary()

                val dayOneData = rep.getDayOneData("Ukraine")

                println("")
            } catch (e: Throwable) {
                e.printStackTrace()
                println(e)
            }

            println("OK!")
        }
    }
}