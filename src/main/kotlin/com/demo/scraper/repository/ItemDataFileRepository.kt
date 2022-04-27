package com.demo.scraper.repository

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import java.io.File

@Repository
class ItemDataFileRepository : ItemRepository {
    private val file = "data/item.data"
    private val dataFile: File = ClassPathResource(file).file

    override fun getUrl(code: String): String? {
        return dataFile.readLines().stream()
            .filter{ it.endsWith("/$code") }
            .findFirst()
            .orElse(null)
    }
}