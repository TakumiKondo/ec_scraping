package com.demo.scraper.model

import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.block.SectionBlock
import com.slack.api.model.block.composition.MarkdownTextObject


data class DoclasseItem (
    var code: String?,
    var name: String?,
    // Map<Color<colorCode, colorName>, StockSize>
    var scs: Map<Pair<String?, String?>?, List<String>?>,
    var link: String?,
) :Item {
    private fun sizeListView(): String {
        return scs.map {
            (it.key?.second + "（" + it.key?.first + "）×  " + it.value?.reduce { acc, s -> "$acc, $s" })
        }.reduce { acc, s -> "$acc \n $s" }
    }

    override fun sections(): List<LayoutBlock> {
        return mutableListOf<LayoutBlock>(
            SectionBlock(MarkdownTextObject("*商品コード:*\n${code}", false), null, null, null),
            SectionBlock(MarkdownTextObject("*商品名:*\n<${link}|${name}>", false), null, null, null),
            SectionBlock(MarkdownTextObject("*カラー × 在庫サイズ:*\n${sizeListView()}", false), null, null, null),
        )
    }
}