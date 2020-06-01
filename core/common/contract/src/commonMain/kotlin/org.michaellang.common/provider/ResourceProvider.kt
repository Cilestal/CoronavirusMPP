package org.michaellang.common.provider

import dev.icerock.moko.resources.StringResource

interface ResourceProvider {
    fun getString(res: StringResource): String
}