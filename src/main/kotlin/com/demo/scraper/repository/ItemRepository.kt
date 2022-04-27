package com.demo.scraper.repository

interface ItemRepository {
    fun getUrl(code: String): String?
}