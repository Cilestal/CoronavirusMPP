package org.michaellang.common.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class RequiredBindings(
    vararg val classes: KClass<*>
)