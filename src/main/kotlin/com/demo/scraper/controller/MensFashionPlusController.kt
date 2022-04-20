package com.demo.scraper.controller

import com.demo.scraper.model.Item
import com.demo.scraper.service.ItemFormatter
import com.demo.scraper.service.Scraping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MensFashionPlusController {
    // 取得処理(ECサイトから商品情報を取得)
    @GetMapping("/")
    fun getItem(): Item {
        // TODO: 一旦仮で固定値
        val url = "https://mensfashion.cc/c/mane/lacw/lac/C6751"
        // inのデータ(HTML)
        val doc = Scraping(url).get()
        // 加工(必要な商品情報の抽出) && outのデータ(送信用データ)
        val item = ItemFormatter(doc).format()
        return item

        // JSONが返るか検証（NG）
//        return Item("hoge", "huga", listOf("M", "L"))

        // commit test
        // TODO: 送信処理(Slack通知とかNotify系)
    }

}