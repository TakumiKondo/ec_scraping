package com.demo.scraper.service

import com.demo.scraper.exception.UrlNotFoundException
import com.demo.scraper.model.Item
import com.demo.scraper.repository.ItemRepository
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ItemService {
    @Autowired
    private lateinit var itemRepository: ItemRepository

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

    private fun toItem(doc: Document, url: String): Item {
        val code: String = doc.select("span.fs-c-productNumber__number").first()?.text().toString()
        val name: String = doc.select("span.fs-c-productNameHeading__name").first()?.text().toString()
        val size = doc.select("span.fs-c-variationCart__variationName__name")
            .filter {
                val ele = it.parent()?.select("span.fs-c-variationCart__variationName__stock--outOfStock")
                !(ele != null && "在庫切れ" == ele.text())
            }.map {
                it.text()
            }
        return Item(code, name, size.stream().collect(Collectors.toList()), url)
    }
}