package com.kuro9.karaokespring.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app")
data class AppConfig(
    val webhook: Webhook,
) {

    data class Webhook(
        val devErrorChannel: String,
        val prodErrorChannel: String,
    )
}
