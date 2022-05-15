package com.demo.scraper.repository

import org.springframework.stereotype.Repository

@Repository
class MensFashionPlusItemDataFileRepository : ItemDataFileRepository("data/MensFashionPlusItem.data") {
    override fun hasLine(code: String, line: String): Boolean {
        return line.endsWith("/$code")
    }
}