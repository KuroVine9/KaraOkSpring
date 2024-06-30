package com.kuro9.karaokespring.repository.condition

import com.kuro9.karaokespring.interfaces.Song
import java.time.LocalDate

sealed interface NewSongSearchCondition {

    data class PK(
        val id: Long,
        val brand: Song.Brand
    ) : NewSongSearchCondition

    data class Range(
        val brand: Song.Brand,
        val dateRange: ClosedRange<LocalDate>
    ) : NewSongSearchCondition
}