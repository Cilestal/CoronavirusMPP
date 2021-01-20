package org.michaellang.common.provider

interface DateTimeProvider {
    fun getTimeMills(): Long
    fun getCurrentDateTime(format: String, timeZone: String? = UTC_TIMEZONE): String

    fun format(date: Long, dateFormat: String): String
    fun parse(date: String, format: String): Long

    companion object {
        const val UTC_TIMEZONE = "UTC"
    }
}