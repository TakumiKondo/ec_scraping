package com.demo.scraper.repository

import org.springframework.core.io.ClassPathResource
import java.io.*

// TODO: 6. ItemDataFileRepositoryを抽象クラスにして、サイト固有のフィールドを持たなくする
abstract class ItemDataFileRepository(file: String) : ItemRepository {
    private var file: String
    private var dataFileResource: ClassPathResource

    init {
        this.file = file
        this.dataFileResource = ClassPathResource(file)
    }

    override fun getUrl(code: String): String? {
        val `in` = dataFileResource.inputStream
        val buffered = BufferedReader(InputStreamReader(`in`))
        var line: String?
        while (true) {
            line = buffered.readLine()
            if (line == null) return null
            if (line.endsWith("/$code")) return line
        }
    }
}