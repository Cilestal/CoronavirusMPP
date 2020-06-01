package org.michaellang.common.provider

class DateTimeProviderImpl : DateTimeProvider {
    override fun getTimeMills(): Long {
        return System.currentTimeMillis()
    }

    override fun parse(date: String, format: String): Long {
        return 11111
    }

    override fun getCurrentDateTime(format: String, timeZone: String?): String {
        return "200-2220"
    }

    override fun format(date: Long, dateFormat: String): String {
        return "200-2220"
    }

}