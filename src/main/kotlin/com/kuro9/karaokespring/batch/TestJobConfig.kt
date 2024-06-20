package com.kuro9.karaokespring.batch

import org.quartz.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TestJobConfig {

    @Bean
    fun testJobDetail(): JobDetail =
        JobBuilder.newJob(TestJob::class.java)
            .withIdentity("testJob")
            .storeDurably()
            .build()

    @Bean
    fun testJobTrigger(): Trigger =
        TriggerBuilder.newTrigger()
            .forJob(testJobDetail())
            .withSchedule(CronScheduleBuilder.cronSchedule("0/5  * * * * ?"))
            .build()
}