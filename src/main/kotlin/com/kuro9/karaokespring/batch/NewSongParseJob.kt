package com.kuro9.karaokespring.batch

import com.kuro9.karaokespring.api.tj.TjHomePageParseService
import com.kuro9.karaokespring.util.infoLog
import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component

@Component
class NewSongParseJob(
    private val tjPageParseService: TjHomePageParseService,
) : QuartzJobBean() {


    override fun executeInternal(context: JobExecutionContext) {
        infoLog("===== BEGIN ${this::class.simpleName} =====")
        val newSongList = tjPageParseService.get()
    }
}