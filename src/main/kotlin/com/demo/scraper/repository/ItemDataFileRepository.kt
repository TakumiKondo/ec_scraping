package com.demo.scraper.repository

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import java.io.*

@Repository
class ItemDataFileRepository : ItemRepository {
    private val file = "data/item.data"
    private val dataFileResource = ClassPathResource(file)

    override fun getUrl(code: String): String? {
        val `in` = dataFileResource.inputStream
        val buffered = BufferedReader(InputStreamReader(`in`))
        var line: String? = null
        while (true) {
            line = buffered.readLine()
            if (line == null) return null
            if (line.endsWith("/$code")) return line
        }
    }
}