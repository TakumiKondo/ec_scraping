package com.demo.scraper.controller

import com.demo.scraper.service.ItemService
import com.demo.scraper.service.SlackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MensFashionPlusController {
    @Autowired
    lateinit var itemService: ItemService

    @Autowired
    private lateinit var slackService: SlackService

    // 取得処理(ECサイトから商品情報を取得)
    @GetMapping("/item/{code}")
    fun getItem(@PathVariable code: String) {
        val item = itemService.getItem(code)
        slackService.notify(item)

        // TODO: http statusを返すか検討
    }

    // TODO: Exceptionのハンドリングどうするか検討
}