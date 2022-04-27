package com.demo.scraper.service

import com.demo.scraper.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ItemService() {
    @Autowired
    private lateinit var itemRepository: ItemRepository

    fun getUrl(code: String): String? {
        return itemRepository.getUrl(code)
    }
}