package com.demo.scraper.service

import org.jsoup.nodes.Element
import org.springframework.stereotype.Component

@Component
interface StockBehavior {
    fun hasStock(siteType: SiteType, ele: Element?): Boolean
}