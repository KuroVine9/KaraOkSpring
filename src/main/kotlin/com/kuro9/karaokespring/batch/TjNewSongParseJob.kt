package com.kuro9.karaokespring.batch

import com.kuro9.karaokespring.api.tj.TjHomePageParseService
import com.kuro9.karaokespring.batch.job.DefaultJob
import com.kuro9.karaokespring.interfaces.Song
import com.kuro9.karaokespring.repository.NewSongLogRepo
import com.kuro9.karaokespring.repository.condition.NewSongSearchCondition
import com.kuro9.karaokespring.repository.entity.NewSong
import com.kuro9.karaokespring.util.infoLog
import com.kuro9.karaokespring.util.toRangeOfMonth
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Component
import java.time.YearMonth

@Component
class TjNewSongParseJob(
    private val tjPageParseService: TjHomePageParseService,
    private val newSongRepo: NewSongLogRepo
) : DefaultJob() {

    override fun doExecute(context: JobExecutionContext) {
        val newSongList = tjPageParseService.get()
        val savedNewSong: List<Song> = newSongRepo.find(
            NewSongSearchCondition.Range(
                brand = Song.Brand.TJ,
                dateRange = YearMonth.now().toRangeOfMonth()
            )
        )

        newSongList.filter { it !in savedNewSong }.also {
            infoLog("filtered Song Count=${it.size}")
        }.forEach {
            newSongRepo.save(
                NewSong(
                    id = it.id,
                    brand = Song.Brand.TJ,
                    title = it.title,
                    lyricist = it.lyricist,
                    singer = it.singer,
                    composer = it.composer
                )
            )
        }
    }
}