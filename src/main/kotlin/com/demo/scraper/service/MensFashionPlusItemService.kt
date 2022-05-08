package com.demo.scraper.service

import com.demo.scraper.model.Item
import com.demo.scraper.model.MensFashionPlusItem
import com.demo.scraper.repository.MensFashionPlusItemDataFileRepository
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service("MensFashionPlusItemService")
class MensFashionPlusItemService(itemRepository: MensFashionPlusItemDataFileRepository) : ItemService(itemRepository){

    // サイト固有部分の実装
    override fun toItem(doc: Document, url: String): Item {
        val code: String = doc.select("span.fs-c-productNumber__number").first()?.text().toString()
        val name: String = doc.select("span.fs-c-productNameHeading__name").first()?.text().toString()
        val size = doc.select("span.fs-c-variationCart__variationName__name")
            .filter {
                val ele = it.parent()?.select("span.fs-c-variationCart__variationName__stock--outOfStock")
                !(ele != null && "在庫切れ" == ele.text())
            }.map {
                it.text()
            }
        return MensFashionPlusItem(code, name, size.stream().collect(Collectors.toList()), url)
    }
}