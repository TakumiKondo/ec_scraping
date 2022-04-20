package com.demo.scraper.model

data class Item(
    private var code: String?,
    private var name: String?,
    private var stocks: List<String>?
)