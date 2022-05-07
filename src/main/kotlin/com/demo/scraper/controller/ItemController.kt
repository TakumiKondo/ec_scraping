package com.demo.scraper.controller

import com.demo.scraper.model.ItemResponse
import com.demo.scraper.service.ItemService
import com.demo.scraper.service.SlackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

// TODO: 違うサイトの商品も対応したい
// TODO: 1. 他サイト用のControllerを用意する
// TODO: 2. リクエストURLをサイト別に定義し直す
// TODO: 3. 他サイト用のServiceを用意する
// TODO: 5. 他サイト固有のHTMLからデータ取得部分を実装する（個別実装できるようにする）
abstract class ItemController(private val itemService: ItemService) {

    @Autowired
    private lateinit var slackService: SlackService

    /**
     * 継承先ではリクエストURLを指定させるだけの想定
     */
    open fun getItem(@PathVariable code: String) : ResponseEntity<ItemResponse> {
        val item = itemService.getItem(code)
        slackService.notify(item)

        return ResponseEntity(ItemResponse(HttpStatus.OK.value(), code), HttpStatus.OK)
    }
}