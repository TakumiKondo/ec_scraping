package com.demo.scraper.model

import com.slack.api.model.block.LayoutBlock

interface Item {
    fun sections(): List<LayoutBlock>
}