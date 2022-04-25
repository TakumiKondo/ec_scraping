package com.demo.scraper.controller

import com.demo.scraper.service.ItemFormatter
import com.demo.scraper.service.Scraping
import com.slack.api.Slack
import com.slack.api.methods.request.chat.ChatPostMessageRequest.ChatPostMessageRequestBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MensFashionPlusController {
    // 取得処理(ECサイトから商品情報を取得)
    // TODO: 商品情報をパラメータで指定できるようにする
    @GetMapping("/")
    fun getItem() {
        // TODO: URLの生成、スクレイピング、データ加工、Slack連携をロジック化する
        val url = "https://mensfashion.cc/c/mane/lacw/lac/C6751"
        // inのデータ(HTML)
        val doc = Scraping(url).get()
        // 加工(必要な商品情報の抽出) && outのデータ(送信用データ)
        val item = ItemFormatter(doc).format()

        // TODO: Service層(？)に移動する
        /**
         * 必要なApp側の設定
         * ・Slack APIで新規アプリをスクラッチで作成
         * ・PermissionのBot/Userに、chat:writeを設定
         * ・アプリの名称とユーザ名を設定
         * ・アプリのインストール
         * ・Tokenの発行
         */
        val token = System.getenv("SLACK_TOKEN")
        val slack = Slack.getInstance();
        val response = slack.methods(token).chatPostMessage { req: ChatPostMessageRequestBuilder ->
            req
                .channel(System.getenv("SLACK_CHANNEL")) 
                .text(item.toString())
        }
        println(response.toString())

        // TODO: http statusを返すか検討
    }

}