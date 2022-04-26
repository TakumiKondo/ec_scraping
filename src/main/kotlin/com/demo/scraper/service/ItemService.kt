package com.demo.scraper.service

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.File

@Service
class ItemService() {
    // TODO: データファイルはRepository層に移動
    private val file = "data/item.data"
    private val dataFile: File = ClassPathResource(file).file

    fun getUrl(code: String): String? {
        return dataFile.readLines().stream()
            .filter{ it.endsWith("/$code") }
            .findFirst()
            .orElse(null)
    }
}