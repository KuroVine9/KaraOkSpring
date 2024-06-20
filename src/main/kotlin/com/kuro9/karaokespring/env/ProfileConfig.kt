package com.kuro9.karaokespring.env

import com.kuro9.karaokespring.config.AppConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ProfileConfig(private val appConfig: AppConfig) {

    @Bean
    @Profile("Dev", "Local", "default")
    fun getDevWebhook() = Webhook(appConfig.webhook.devErrorChannel)

    @Bean
    @Profile("Alp")
    fun getAlpWebhook() = Webhook(appConfig.webhook.devErrorChannel, false)

    @Bean
    @Profile("Prod")
    fun getProdWebhook() = Webhook(appConfig.webhook.prodErrorChannel)

    data class Webhook(
        val errorWebhookChannel: String,
        val isErrorWebhookEnabled: Boolean = true
    )
}