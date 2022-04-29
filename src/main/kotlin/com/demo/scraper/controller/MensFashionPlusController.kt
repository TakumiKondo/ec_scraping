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