package com.kuro9.karaokespring.controller

import com.kuro9.karaokespring.api.tj.TjHomePageParseService
import com.kuro9.karaokespring.interfaces.Song
import com.kuro9.karaokespring.repository.NewSongLogRepo
import com.kuro9.karaokespring.repository.condition.NewSongSearchCondition
import com.kuro9.karaokespring.repository.entity.NewSong
import com.kuro9.karaokespring.util.toRangeOfMonth
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.YearMonth

@RestController
@RequestMapping("/test")
class TestController(
    private val tj: TjHomePageParseService,
    private val repo: NewSongLogRepo
) {
    private val logger = LoggerFactory.getLogger(TestController::class.java)

    @GetMapping
    fun test(): List<NewSong> {
        logger.info("testlog")
        return repo.find(
            NewSongSearchCondition.Range(
                Song.Brand.TJ, YearMonth.now().toRangeOfMonth()
            )
        )
    }
}