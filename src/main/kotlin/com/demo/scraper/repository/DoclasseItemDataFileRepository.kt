package com.demo.scraper.repository

import org.springframework.stereotype.Repository

@Repository
class DoclasseItemDataFileRepository : ItemDataFileRepository("data/DoclasseItem.data") {
    override fun hasLine(code: String, line: String): Boolean {
        return line.endsWith("/g$code")
    }
}