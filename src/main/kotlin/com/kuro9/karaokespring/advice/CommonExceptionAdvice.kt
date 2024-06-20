package com.kuro9.karaokespring.advice

import com.kuro9.karaokespring.config.AppConfig
import com.kuro9.karaokespring.exception.CodeAssignException
import com.kuro9.karaokespring.service.WebhookService
import com.kuro9.karaokespring.util.errorLog
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CommonExceptionAdvice(
    private val webhookService: WebhookService,
    private val appConfig: AppConfig,
) {
    @ExceptionHandler(Throwable::class)
    fun unknownException(e: Throwable, request: HttpServletRequest): ResponseEntity<Any> {
        errorLog("Error Occurred: ", e)
        webhookService.sendWebhook(appConfig.webhook.devErrorChannel, e, request)

        return when (e) {
            is CodeAssignException -> ResponseEntity.status(e.httpStatus).build()
            else -> ResponseEntity.internalServerError().build()
        }
    }
}