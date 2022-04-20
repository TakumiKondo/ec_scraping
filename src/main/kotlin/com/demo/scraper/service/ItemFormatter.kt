package com.demo.scraper.service

import com.demo.scraper.model.Item
import org.jsoup.nodes.Document
import java.util.stream.Collectors

class ItemFormatter(private val doc: Document?) {

    fun format(): Item {
        val code: String = doc?.select("span.fs-c-productNumber__number")?.first()?.text().toString()
        val name: String = doc?.select("span.fs-c-productNameHeading__name")?.first()?.text().toString()
        val stocks = doc?.select("span.fs-c-variationCart__variationName__name")?.map { it.text() }
        return Item(name, code, stocks?.stream()?.collect(Collectors.toList()))
    }
}
