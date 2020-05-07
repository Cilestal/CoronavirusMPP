package org.michaellang.mviproject.di

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.michaellang.common.DateTimeProvider
import org.michaellang.common.di.JsonModule
import org.michaellang.mviproject.App
import kotlin.reflect.KProperty

class AppModule(
    app: Application
) : KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(androidXModule(app))
        import(JsonModule().module)

        //todo test
        bind<DateTimeProvider>() with singleton {
            object : DateTimeProvider {
                override fun getTimeMills(): Long {
                    return System.currentTimeMillis()
                }

                override fun getCurrentDateTime(format: String, timeZone: String?): String {
                    return "123"
                }

                override fun format(
                    date: String,
                    sourceFormat: String,
                    dateFormat: String
                ): String {
                    return "123"
                }
            }
        }

        import(AndroidDataModule(app).module)
    }

    operator fun getValue(app: App, property: KProperty<*>): Kodein {
        return kodein
    }

}
