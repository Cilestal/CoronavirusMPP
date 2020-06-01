package org.michaellang.common.provider

import android.content.Context
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {
    override fun getString(res: StringResource): String {
        return StringDesc.Resource(res).toString(context)
    }
}