package com.demo.scraper.controller

import com.demo.scraper.service.ItemFormatter
import com.demo.scraper.service.Scraping
import com.demo.scraper.service.ItemService
import com.slack.api.Slack
import com.slack.api.methods.request.chat.ChatPostMessageRequest.ChatPostMessageRequestBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MensFashionPlusController {
    @Autowired
    lateinit var itemService: ItemService

    // 取得処理(ECサイトから商品情報を取得)
    @GetMapping("/item/{code}")
    fun getItem(@PathVariable code: String) {
        // TODO: URLの生成、スクレイピング、データ加工、Slack連携をロジック化する
        // item.dataファイルで商品URLを外部ファイル管理
        // TODO: item.dataを差し替えても動作するか外だしファイルとしての動作確認
        val url = itemService.getUrl(code) ?: throw IllegalStateException("has not url of this code: $code")
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