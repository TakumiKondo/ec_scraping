package com.demo.scraper.service

import org.jsoup.nodes.Element

class DevStockBehavior : StockBehavior {
    override fun hasStock(siteType: SiteType, ele: Element?): Boolean {
        return true
    }
}