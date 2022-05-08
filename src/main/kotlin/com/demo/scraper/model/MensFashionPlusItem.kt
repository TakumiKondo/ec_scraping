package com.demo.scraper.model

import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.block.SectionBlock
import com.slack.api.model.block.composition.MarkdownTextObject

data class MensFashionPlusItem(
    var code: String?,
    var name: String?,
    var size: List<String>?,
    var link: String?,
) : Item {
    internal fun sizeListView(): String {
        return size?.reduce { acc, s -> "$acc, $s" } ?: ""
    }

    override fun sections(): List<LayoutBlock> {
        return mutableListOf<LayoutBlock>(
            SectionBlock(MarkdownTextObject("*商品コード:*\n${code}", false), null, null, null),
            SectionBlock(MarkdownTextObject("*商品名:*\n<${link}|${name}>", false), null, null, null),
            SectionBlock(MarkdownTextObject("*在庫サイズ:*\n${sizeListView()}", false), null, null, null),
        )
    }
}