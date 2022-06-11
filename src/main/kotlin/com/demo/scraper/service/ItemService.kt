package com.demo.scraper.service

import com.demo.scraper.exception.UrlNotFoundException
import com.demo.scraper.model.Item
import com.demo.scraper.repository.ItemRepository
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Autowired

abstract class ItemService(private var itemRepository: ItemRepository) {
    @Autowired
    lateinit var stockBehavior: StockBehavior

    fun getItem(code: String): Item {
        val url = getUrl(code) ?: throw UrlNotFoundException("code[$code] not found in data.")
        val doc = getDoc(url)
        return toItem(doc, url)
    }

    internal fun getUrl(code: String): String? {
        return itemRepository.getUrl(code)
    }

    internal fun getDoc(url: String): Document {
        return Scraping(url).get()
    }

    internal abstract fun toItem(doc: Document, url: String): Item

    // TODO: 削除
    internal abstract fun hasStock(ele: Element?): Boolean
}