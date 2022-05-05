package com.demo.scraper.service

import com.demo.scraper.model.Item
import com.demo.scraper.util.EnvUtil
import com.slack.api.Slack
import com.slack.api.methods.kotlin_extension.request.chat.blocks
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class SlackService {
    fun notify(item: Item) {
        /**
         * 必要なApp側の設定
         * ・Slack APIで新規アプリをスクラッチで作成
         * ・PermissionのBot/Userに、chat:writeを設定
         * ・アプリの名称とユーザ名を設定
         * ・アプリのインストール
         * ・Tokenの発行
         */
        val token = EnvUtil.slackToken()
        val slack = Slack.getInstance()
        val response = slack.methods(token).chatPostMessage { req: ChatPostMessageRequest.ChatPostMessageRequestBuilder ->
            req
                .channel(EnvUtil.slackChannel())
                .blocks {
                    section { markdownText("*商品コード:*\n${item.code}") }
                    section { markdownText("*商品名:*\n<${item.link}|${item.name}>") }
                    section { markdownText("*在庫サイズ:*\n${item.sizeListView()}") }
                }
        }

        println(response.toString())
    }
}