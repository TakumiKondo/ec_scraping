package com.demo.scraper.service

import com.demo.scraper.model.Item
import com.demo.scraper.util.EnvUtil
import com.slack.api.Slack
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
        // TODO: slackのメッセージを読みやすくする（入荷してたらステータスを緑にして通知するとか）
        val response = slack.methods(token).chatPostMessage { req: ChatPostMessageRequest.ChatPostMessageRequestBuilder ->
            req
                .channel(EnvUtil.slackChannel())
                .text(item.toString())
        }

        println(response.toString())
    }
}