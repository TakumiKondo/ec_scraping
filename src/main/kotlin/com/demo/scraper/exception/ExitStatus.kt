package com.demo.scraper.exception

enum class ExitStatus(val value: Int) {
    NORMAL_END(0),
    FAILED_TO_START(1),
}