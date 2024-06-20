package com.kuro9.karaokespring.controller

import com.kuro9.karaokespring.api.tj.TJ
import com.kuro9.karaokespring.api.tj.TjSongInfo
import com.kuro9.karaokespring.exception.CodeAssignException
import com.kuro9.karaokespring.repository.TestRepo
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(
    private val repo: TestRepo,
    private val tj: TJ
) {
    private val logger = LoggerFactory.getLogger(TestController::class.java)

    @GetMapping
    fun test(): List<TjSongInfo> {
        repo.test()
        logger.info("testlog")
        throw CodeAssignException(HttpStatus.I_AM_A_TEAPOT)
        return tj.get()
    }
}