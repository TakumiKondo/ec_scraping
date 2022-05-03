package com.demo.scraper

import com.demo.scraper.exception.ExitStatus
import com.demo.scraper.util.EnvUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
class ScraperApplication

fun main(args: Array<String>) {
	preCheck()
	runApplication<ScraperApplication>(*args)
}

private fun preCheck() {
	try {
		EnvUtil.slackChannel()
		EnvUtil.slackToken()
	} catch (e: Exception) {
		e.printStackTrace()
		exitProcess(ExitStatus.FAILED_TO_START.value)
	}
}