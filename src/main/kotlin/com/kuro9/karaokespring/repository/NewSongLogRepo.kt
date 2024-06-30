package com.kuro9.karaokespring.repository

import com.kuro9.karaokespring.exception.DatabaseException
import com.kuro9.karaokespring.repository.condition.NewSongSearchCondition
import com.kuro9.karaokespring.repository.dao.NewSongLogDao
import com.kuro9.karaokespring.repository.entity.NewSong
import com.kuro9.karaokespring.util.yyyyMMdd
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class NewSongLogRepo(
    private val dao: NewSongLogDao
) {

    @Transactional(rollbackFor = [Exception::class])
    fun save(song: NewSong) {
        kotlin.runCatching {
            dao.save(song)
        }.onSuccess {
            if (it != 1) throw DatabaseException.InsertFailedException()
        }.getOrThrow()
    }

    fun findByPk(condition: NewSongSearchCondition.PK): NewSong? = dao.findByPk(condition.id, condition.brand.name)

    @Transactional(readOnly = true)
    fun find(condition: NewSongSearchCondition): List<NewSong> = when (condition) {
        is NewSongSearchCondition.PK -> listOfNotNull(findByPk(condition))
        is NewSongSearchCondition.Range -> dao.findByRange(
            brand = condition.brand.name,
            yyyyMMddStart = condition.dateRange.start.yyyyMMdd,
            yyyyMMddEnd = condition.dateRange.endInclusive.yyyyMMdd
        )
    }
}