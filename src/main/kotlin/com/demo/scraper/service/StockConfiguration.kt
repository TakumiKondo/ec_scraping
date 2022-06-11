package com.demo.scraper.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class StockConfiguration {

    @Bean
    @Profile("dev")
    fun devConfig(): DevStockBehavior {
        return DevStockBehavior()
    }

    @Bean
    @Profile("prod")
    fun prodConfig(): ProdStockBehavior {
        return ProdStockBehavior()
    }
}
