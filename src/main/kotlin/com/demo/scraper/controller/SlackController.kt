package com.demo.scraper.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SlackController {

    @GetMapping("/slack")
    fun slack(): String {
        return "slack"
    }
}