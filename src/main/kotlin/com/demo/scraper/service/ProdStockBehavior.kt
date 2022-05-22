package com.demo.scraper.service

import org.jsoup.nodes.Element

class ProdStockBehavior : StockBehavior {
    override fun hasStock(siteType: SiteType, ele: Element?): Boolean {
        if (SiteType.MFPLUS == siteType) {
            if (ele == null) return true
            return "在庫切れ" != ele.text()
        }
        if (SiteType.DOCLASSE == siteType) {
            if (ele == null ) return true
            return !ele.toString().contains(("disabled autocomplete"))
        }
        if (SiteType.TEST == siteType) {
            return false
        }
        return true
    }
}