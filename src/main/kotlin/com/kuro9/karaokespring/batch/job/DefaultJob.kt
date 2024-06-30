package com.kuro9.karaokespring.batch.job

import com.kuro9.karaokespring.service.WebhookService
import com.kuro9.karaokespring.util.ApplicationContextProvider
import com.kuro9.karaokespring.util.errorLog
import com.kuro9.karaokespring.util.infoLog
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.springframework.scheduling.quartz.QuartzJobBean
import java.time.Duration
import java.time.LocalDateTime

abstract class DefaultJob : QuartzJobBean() {

    @Throws(Exception::class)
    abstract fun doExecute(context: JobExecutionContext)

    @Throws(JobExecutionException::class)
    override fun executeInternal(context: JobExecutionContext) {
        val startTime = LocalDateTime.now()
        infoLog("===== BEGIN JOB ${this::class.simpleName} =====")
        kotlin.runCatching {
            doExecute(context)
        }.onFailure {
            infoLog("===== ERROR WHILE DOING JOB ${this::class.simpleName} =====")
            kotlin.runCatching {
                ApplicationContextProvider.getBean(WebhookService::class.java)
                    .sendBatchErrorWebhook(it, this::class.java)
            }.onFailure { e ->
                errorLog("===== ERROR WHILE SENDING WEBHOOK =====", e)
            }
            throw JobExecutionException(it)
        }
        infoLog("===== END JOB ${this::class.simpleName} =====")
        infoLog("===== EXECUTION DURATION : ${Duration.between(startTime, LocalDateTime.now()).seconds} =====")
    }
}