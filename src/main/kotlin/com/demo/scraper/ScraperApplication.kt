package com.demo.scraper

import com.demo.scraper.exception.ExitStatus
import com.demo.scraper.util.EnvUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import kotlin.system.exitProcess

@SpringBootApplication
@EnableConfigurationProperties
class ScraperApplication

fun main(args: Array<String>) {
	preCheck()
	val ctx: ApplicationContext = runApplication<ScraperApplication>(*args)

	println(ctx.getBean("loadApplicationProperties", LoadApplicationProperties::class).toString())
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