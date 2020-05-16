package org.michaellang.common.provider

class DateTimeProviderImpl : DateTimeProvider {
    override fun getTimeMills(): Long {
        return 233
    }

    override fun getCurrentDateTime(format: String, timeZone: String?): String {
        return "200-2220"
    }

    override fun format(date: String, sourceFormat: String, dateFormat: String): String {
        return "200-2220"
    }
}