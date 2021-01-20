package org.michaellang.common.model

data class Page(
    val lastPage: Boolean = false,
    val page: Int = 0,
    val pageSize: Int = DEFAULT_PAGE_SIZE
) {


    val start get() = page * pageSize
    val end get() = start + pageSize

    companion object {
        const val DEFAULT_PAGE_SIZE = 15
    }
}