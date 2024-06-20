package com.kuro9.karaokespring.service

import com.kuro9.karaokespring.util.errorLog
import com.kuro9.karaokespring.util.infoLog
import com.kuro9.karaokespring.util.json
import com.kuro9.karaokespring.vo.Embed
import com.kuro9.karaokespring.vo.Field
import com.kuro9.karaokespring.vo.Payload
import jakarta.servlet.http.HttpServletRequest
import kotlinx.serialization.encodeToString
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class WebhookService {
    private val restTemplate = RestTemplateBuilder()
        .setConnectTimeout(Duration.ofSeconds(10))
        .setReadTimeout(Duration.ofSeconds(10))
        .build()

    fun sendWebhook(webhookEndpoint: String, e: Throwable, request: HttpServletRequest) {
        runCatching {
            val jsonString = toJsonString(e, request)
            infoLog(jsonString)

            val req = RequestEntity.post(webhookEndpoint)
                .header("Content-Type", "application/json")
                .body(jsonString)

            val res = restTemplate.exchange(req, String::class.java)

            infoLog("Webhook Response: {}", res.statusCode)
        }.onFailure { errorLog("Webhook Error", it) }
    }

    companion object ErrorMapper {
        fun toJsonString(e: Throwable, request: HttpServletRequest): String {
            val requestUrl = request.requestURL.toString()
            val method = e.stackTrace[0].methodName
            val className = e.stackTrace[0].className

            return json.encodeToString(
                Payload(
                    username = "ExceptionAlert",
                    content = "Exception Occured:\n${className}",
                    embeds = listOf(
                        Embed(
                            title = "Request URL:",
                            description = requestUrl,
                            color = 16711680,
                            fields = listOf(
                                Field(name = "Message:", value = e.localizedMessage ?: ""),
                                Field(name = "Exception Occurred:", value = className),
                                Field(name = "Method:", value = method),
                                Field(name = "Line:", value = e.stackTrace[0].lineNumber.toString()),
                                Field(name = "Exception:", value = e.javaClass.name),
                                Field(
                                    name = "Stack Trace:",
                                    value = "```${e.stackTrace.joinToString("\n").take(1000)}```"
                                )
                            )
                        )
                    )
                )
            )
        }
    }
}