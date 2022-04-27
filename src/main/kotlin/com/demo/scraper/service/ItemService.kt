package com.demo.scraper.service

import com.demo.scraper.model.Item
import com.demo.scraper.repository.ItemRepository
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ItemService {
    @Autowired
    // TODO: item.dataを差し替えても動作するか外だしファイルとしての動作確認
    private lateinit var itemRepository: ItemRepository

    fun getItem(code: String): Item {
        val url = getUrl(code) ?: throw IllegalStateException("has not url of this code: $code")
        val doc = getDoc(url)
        return toItem(doc)
    }

    private fun getUrl(code: String): String? {
        return itemRepository.getUrl(code)
    }

    private fun getDoc(url: String): Document {
        return Scraping(url).get()
    }

    private fun toItem(doc: Document): Item {
        val code: String = doc.select("span.fs-c-productNumber__number").first()?.text().toString()
        val name: String = doc.select("span.fs-c-productNameHeading__name").first()?.text().toString()
        val size = doc.select("span.fs-c-variationCart__variationName__name").map { it.text() }
        return Item(code, name, size.stream().collect(Collectors.toList()))
    }
}