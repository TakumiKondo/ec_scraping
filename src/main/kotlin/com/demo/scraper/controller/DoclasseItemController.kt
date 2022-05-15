package com.demo.scraper.controller

import com.demo.scraper.model.ItemResponse
import com.demo.scraper.service.DoclasseItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DoclasseItemController(itemService: DoclasseItemService) : ItemController(itemService) {

    // 取得処理(ECサイトから商品情報を取得)
    @GetMapping("/doclasse/{code}")
    override fun getItem(@PathVariable code: String) : ResponseEntity<ItemResponse> {
        return super.getItem(code)
    }
}