package com.kuro9.karaokespring.batch

import org.quartz.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TjNewSongParseJobConfig {

    @Bean
    fun tjNewSongParseJobDetail(): JobDetail =
        JobBuilder.newJob(TjNewSongParseJob::class.java)
            .withIdentity("tjNewSongParseJob")
            .storeDurably()
            .build()

    @Bean
    fun tjNewSongParseJobTrigger(): Trigger =
        TriggerBuilder.newTrigger()
            .forJob(tjNewSongParseJobDetail())
            .withSchedule(CronScheduleBuilder.cronSchedule("0 0 9 * * ?"))
            .build()
}