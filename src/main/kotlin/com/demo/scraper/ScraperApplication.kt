package com.demo.scraper

import com.demo.scraper.exception.ExitStatus
import com.demo.scraper.service.SiteType
import com.demo.scraper.service.StockBehavior
import com.demo.scraper.service.StockConfiguration
import com.demo.scraper.util.EnvUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import kotlin.system.exitProcess


@SpringBootApplication
@EnableConfigurationProperties
class ScraperApplication

fun main(args: Array<String>) {
	preCheck()
	val context = AnnotationConfigApplicationContext(StockConfiguration::class.java)
	val config = context.getBean(StockBehavior::class.java)

	val ctx: ApplicationContext = runApplication<ScraperApplication>(*args)

	println(ctx.getBean("loadApplicationProperties", LoadApplicationProperties::class).toString())
	println("hasStock : " + config.hasStock(SiteType.TEST, null))

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