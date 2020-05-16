package org.michaellang.common.provider

interface DateTimeProvider {
    fun getTimeMills(): Long
    fun getCurrentDateTime(format: String, timeZone: String? = UTC_TIMEZONE): String
    fun format(date: String, sourceFormat: String, dateFormat: String): String

    companion object {
        const val UTC_TIMEZONE = "UTC"
    }
}