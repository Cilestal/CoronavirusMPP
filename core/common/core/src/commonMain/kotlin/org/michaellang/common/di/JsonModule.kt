package org.michaellang.common.di

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

class JsonModule {

    @UnstableDefault
    val module = Kodein.Module("common_json_module") {
        bind<Json>() with singleton {
            Json(
                configuration = JsonConfiguration(
                    useArrayPolymorphism = true,
                    ignoreUnknownKeys = true,
                    isLenient = true
                )
            )
        }
    }
}