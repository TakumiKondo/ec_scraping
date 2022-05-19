package com.demo.scraper

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "load-application-properties")
class LoadApplicationProperties {

    var name: String? = null

    override fun toString(): String {
        return "LoadApplicationProperties : $name"
    }
}