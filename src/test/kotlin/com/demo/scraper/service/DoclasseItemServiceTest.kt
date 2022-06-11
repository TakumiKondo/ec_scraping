package com.demo.scraper.service

import com.demo.scraper.model.DoclasseItem
import com.demo.scraper.repository.DoclasseItemDataFileRepository
import org.apache.commons.lang3.StringUtils
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.`is`
import org.jsoup.nodes.Attributes
import org.jsoup.nodes.Element
import org.jsoup.parser.Tag
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class DoclasseItemServiceTest {
    private val sut: DoclasseItemService = DoclasseItemService(DoclasseItemDataFileRepository())
    // TODO: DevStockBehaviorをDI

    /**
     * ItemRepositoryに委譲しているだけなのでテスト不要
     */
    @Test
    fun getUrl() {
    }

    /**
     * Scrapingに委譲しているだけなのでテスト不要
     */
    @Test
    fun getDoc() {
    }

    // TODO: StockBehaviorによってテストが成功すること
    // 置き換えるまでは一旦無効化
    @Test
    @Disabled
    fun toItem() {
        // given
        val code = "16735"

        // when
        val url = sut.getUrl(code)
        val doc = url?.let { sut.getDoc(it) }
        val item = doc?.let { sut.toItem(it, url) } as DoclasseItem

        // then
        val name = "ギマコットン・スレッド編みブルゾン"
        assertAll(
            { MatcherAssert.assertThat(item.code, `is`(code)) },
            { MatcherAssert.assertThat(item.name, `is`(name)) },
            /**
             * 在庫状況で取得結果が変わるため、取得できていることのみをテストする
             * また、サイト構造が変わったことで取得できないことも検知できるため問題ない認識
             */
            { MatcherAssert.assertThat(hasSomeScs(item.scs), `is`(true)) },
            { MatcherAssert.assertThat(item.link, `is`(url)) },
        )
    }

    private fun hasSomeScs(scs: Map<Pair<String?, String?>?, List<String>?>?): Boolean {
        if (scs == null || scs.isEmpty()) return false
        if (StringUtils.isEmpty(scs.map { it.key }.first()?.first) ||
            StringUtils.isEmpty(scs.map { it.key }.first()?.second) ||
            StringUtils.isEmpty(scs.map { it.value }[0]?.get(0))) return false
        return true
    }

    // TODO: 削除
    // TODO: StockBehaviorのテストを作成
    @Test
    fun hasStockFalse() {
        // given
        val tag: Tag = Tag.valueOf("input")
        val attr: Attributes = Attributes().add("id", "goods")
                                            .add("disabled", "")
                                            .add("autocomplete", "off")
        val ele = Element(tag, "", attr)

        // when
        val actual = sut.hasStock(ele)

        // then
        MatcherAssert.assertThat(actual, `is`(false))
    }

    // TODO: 削除
    @Test
    fun hasStockTrue() {
        // given
        val ele: Element? = null

        // when
        val actual = sut.hasStock(ele)

        // then
        MatcherAssert.assertThat(actual, `is`(true))
    }

}