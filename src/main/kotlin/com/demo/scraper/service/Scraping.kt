package com.demo.scraper.service

import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class Scraping(private val url: String) {

    fun get(): Document {
        return Jsoup.connect(url).get()
    }
}
