package com.demo.scraper.service

import com.demo.scraper.exception.UrlNotFoundException
import com.demo.scraper.model.Item
import com.demo.scraper.repository.ItemRepository
import org.jsoup.nodes.Document

abstract class ItemService(private var itemRepository: ItemRepository) {

    fun getItem(code: String): Item {
        val url = getUrl(code) ?: throw UrlNotFoundException("code[$code] not found in data.")
        val doc = getDoc(url)
        return toItem(doc, url)
    }

    private fun getUrl(code: String): String? {
        return itemRepository.getUrl(code)
    }

    private fun getDoc(url: String): Document {
        return Scraping(url).get()
    }

    protected abstract fun toItem(doc: Document, url: String): Item
}