package com.kuro9.karaokespring.repository.condition

import com.kuro9.karaokespring.repository.entity.NewSong
import java.time.LocalDate

sealed interface NewSongSearchCondition {

    data class PK(
        val id: Long,
        val brand: NewSong.Brand
    ) : NewSongSearchCondition

    data class Range(
        val id: Long,
        val brand: NewSong.Brand,
        val dateRange: ClosedRange<LocalDate>
    ) : NewSongSearchCondition
}