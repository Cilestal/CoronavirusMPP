package org.michaellang.common.provider

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

class IosResourceProviderImpl : ResourceProvider {
    override fun getString(res: StringResource): String {
        return StringDesc.Resource(res).toString()
    }
}