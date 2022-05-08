package com.demo.scraper.service

import com.demo.scraper.model.DoclasseItem
import com.demo.scraper.model.Item
import com.demo.scraper.repository.DoclasseItemDataFileRepository
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service("DoclasseItemService")
class DoclasseItemService(itemRepository: DoclasseItemDataFileRepository) : ItemService(itemRepository) {
    private val scsDelimiter = " "

    override fun toItem(doc: Document, url: String): Item {
        val code: String = doc.selectFirst("dd#spec_goods")?.text().toString().replace("商品番号 ", "")
        val name: String = doc.selectFirst("div.block-goods-name h1")?.text().toString()
        val elements = doc.select("li.block-variation--item")
        val scs: MutableMap<Pair<String?, String?>?, List<String>?> = mutableMapOf()
        elements.forEach {
            val keyPairText: List<String>? = it.select("figcaption").first()?.text()?.split(scsDelimiter)
            val keyPair: Pair<String?, String?> = Pair(keyPairText?.get(0), keyPairText?.get(1).toString())
            val size: List<String> = it.select("span.block-variation--radio-label")
              // 在庫なしの場合の表記が不明なことと予約注文が可能なことから、一旦全サイズを在庫有りとして取得する
//            .filter {
//            }
                .map {
                     eachSize -> eachSize.text()
                }
            scs[keyPair] = size
        }

        return DoclasseItem(code, name, scs, url)
    }
}