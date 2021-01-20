package org.michaellang.common.di

import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

class JsonCoreModule {

    val module = DI.Module("common_json_module") {
        bind<Json>() with singleton {
            Json(builderAction = {
                useArrayPolymorphism = true
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }
}