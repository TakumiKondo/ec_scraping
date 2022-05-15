package com.demo.scraper.exception

import java.time.LocalDateTime

class UrlErrorResponse(var status: Int? = null,
                       var message: String? = null,
                       var timestamp: LocalDateTime? = null)
