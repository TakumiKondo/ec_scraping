package com.demo.scraper.service

import com.demo.scraper.model.DoclasseItem
import com.demo.scraper.model.Item
import com.demo.scraper.repository.DoclasseItemDataFileRepository
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("DoclasseItemService")
class DoclasseItemService(itemRepository: DoclasseItemDataFileRepository) : ItemService(itemRepository)
{
//    @Autowired
//    private lateinit var stockBehavior: StockBehavior

    private val scsDelimiter = " "

    override fun toItem(doc: Document, url: String): Item {
        val code: String = doc.selectFirst("dd#spec_goods")?.text().toString().replace("商品番号 ", "")
        val name: String = doc.selectFirst("div.block-goods-name h1")?.text().toString()
        val elements = doc.select("li.block-variation--item")
        val scs: MutableMap<Pair<String?, String?>?, List<String>?> = mutableMapOf()
        elements.forEach {
            val keyPairText: List<String>? = it.select("figcaption").first()?.text()?.split(scsDelimiter)
            val keyPair: Pair<String?, String?> = Pair(keyPairText?.get(1), keyPairText?.get(0).toString())
            val size: List<String> = it.select("span.block-variation--radio-label")
                .filter { sizeIt ->
                    val sizeEach = sizeIt.parent()?.select("input#goods")?.first()
                    stockBehavior.hasStock(SiteType.DOCLASSE, sizeEach)
                }
                .map {
                     eachSize -> eachSize.text()
                }
            scs[keyPair] = size
        }

        return DoclasseItem(code, name, scs, url)
    }

    // TODO: 削除
    override fun hasStock(ele: Element?): Boolean {
        if (ele == null ) return true
        return !ele.toString().contains(("disabled autocomplete"))
    }
}