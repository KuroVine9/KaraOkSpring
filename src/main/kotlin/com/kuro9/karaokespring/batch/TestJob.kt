package com.kuro9.karaokespring.batch

import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.scheduling.quartz.QuartzJobBean

class TestJob : QuartzJobBean() {
    private val logger = LoggerFactory.getLogger(TestJob::class.java)
    override fun executeInternal(context: JobExecutionContext) {
        logger.info("Starting execution")
        val num: Long = (0L..0xFFFFFFL).sum()
        logger.info("job executed! $num")
    }
}