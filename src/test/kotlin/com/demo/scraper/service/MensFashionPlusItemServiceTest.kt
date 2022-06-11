package com.demo.scraper.service

import com.demo.scraper.model.MensFashionPlusItem
import com.demo.scraper.repository.MensFashionPlusItemDataFileRepository
import org.apache.commons.lang3.StringUtils
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.jsoup.nodes.Element
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled

internal class MensFashionPlusItemServiceTest {
    private val sut: MensFashionPlusItemService = MensFashionPlusItemService(MensFashionPlusItemDataFileRepository())
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
        val code = "S3815"

        // when
        val url = sut.getUrl(code)
        val doc = url?.let { sut.getDoc(it) }
        val item = doc?.let { sut.toItem(it, url) } as MensFashionPlusItem

        // then
        val name = "ランダムテレコVネック七分袖Tシャツ（ライトグレー）"
        assertAll(
            {assertThat(item.code, `is`(code))},
            {assertThat(item.name, `is`(name))},
            /**
             * 在庫状況で取得結果が変わるため、取得できていることのみをテストする
             * また、サイト構造が変わったことで取得できないことも検知できるため問題ない認識
              */
            {assertThat(hasSomeSize(item.size), `is`(true))},
            {assertThat(item.link, `is`(url))},
        )
    }

    private fun hasSomeSize(size: List<String>?): Boolean {
        if (size == null || size.isEmpty()) return false
        if (StringUtils.isEmpty(size[0])) return false
        return true
    }

    // TODO: 削除
    // TODO: StockBehaviorのテストを作成
    @Test
    fun hasStockFalse() {
        // given
        val ele: Element = Element("span")
            .addClass("fs-c-variationCart__variationName__stock")
            .addClass("fs-c-variationCart__variationName__stock--outOfStock")
            .append("在庫切れ")

        // when
        val actual = sut.hasStock(ele)

        // then
        assertThat(actual, `is`(false))
    }

    // TODO: 削除
    @Test
    fun hasStockTrue() {
        // given
        val ele: Element? = null

        // when
        val actual = sut.hasStock(ele)

        // then
        assertThat(actual, `is`(true))
    }
}