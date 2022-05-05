package com.demo.scraper.model


data class Item(
    var code: String?,
    var name: String?,
    var size: List<String>?,
    var link: String?,
) {
    fun sizeListView(): String {
        return size?.reduce { acc, s -> "$acc, $s" } ?: ""
    }
}