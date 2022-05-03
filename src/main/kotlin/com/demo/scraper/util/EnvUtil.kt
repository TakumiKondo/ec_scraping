package com.demo.scraper.util

import org.springframework.core.io.ClassPathResource
import java.util.*


class EnvUtil {
    companion object {
        private var prop = Properties()
        private val resource = ClassPathResource("env.properties")

        fun slackChannel(): String {
            return envValue("slack.channel")
        }

        fun slackToken(): String {
            return envValue("slack.token")
        }

        private fun envValue(envName: String): String {
            prop.load(resource.inputStream)
            val envValue = prop.getProperty(envName) ?: throw IllegalStateException("[$envName is undefined in properties.")
            return System.getenv(envValue)
        }
    }

}