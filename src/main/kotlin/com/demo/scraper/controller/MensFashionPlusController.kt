package com.demo.scraper.controller

import com.demo.scraper.model.ItemResponse
import com.demo.scraper.service.ItemService
import com.demo.scraper.service.SlackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.http.HttpResponse

// TODO: 違うサイトの商品も対応したい
// TODO: 1. 他サイト用のControllerを用意する
// TODO: 2. リクエストURLをサイト別に定義し直す
// TODO: 3. 他サイト用のServiceを用意する
// TODO: 4. Serviceの抽象親クラスを作成し共通機能を切り出す
// TODO: 5. 他サイト固有のHTMLからデータ取得部分を実装する（個別実装できるようにする）
@RestController
class MensFashionPlusController {
    @Autowired
    lateinit var itemService: ItemService

    @Autowired
    private lateinit var slackService: SlackService

    // 取得処理(ECサイトから商品情報を取得)
    @GetMapping("/item/{code}")
    fun getItem(@PathVariable code: String) : ResponseEntity<ItemResponse> {
        val item = itemService.getItem(code)
        slackService.notify(item)

        return ResponseEntity(ItemResponse(HttpStatus.OK.value(), code), HttpStatus.OK)
    }
}