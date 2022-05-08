package com.demo.scraper.controller

import com.demo.scraper.model.ItemResponse
import com.demo.scraper.service.ItemService
import com.demo.scraper.service.MensFashionPlusItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MensFashionPlusItemController(itemService: MensFashionPlusItemService) : ItemController(itemService) {
    @Autowired
    @Qualifier("MensFashionPlusItemService")
    private lateinit var itemService: ItemService

    // 取得処理(ECサイトから商品情報を取得)
    @GetMapping("/mfplus/{code}")
    override fun getItem(@PathVariable code: String) : ResponseEntity<ItemResponse> {
        return super.getItem(code)
    }
}