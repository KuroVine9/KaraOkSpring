package com.kuro9.karaokespring.repository.entity

import com.kuro9.karaokespring.util.yyyyMMdd
import java.time.LocalDateTime

data class NewSong(
    val id: Long,
    val brand: Brand,
    val title: String,
    val singer: String,
    val lyricist: String,
    val composer: String,
    val yyyyMMdd: String,
    val createdAt: LocalDateTime
) {

    constructor(
        id: Long,
        brand: Brand,
        title: String,
        singer: String,
        lyricist: String,
        composer: String
    ) : this(
        id = id,
        brand = brand,
        title = title,
        singer = singer,
        lyricist = lyricist,
        composer = composer,
        yyyyMMdd = LocalDateTime.now().yyyyMMdd,
        createdAt = LocalDateTime.now()
    )

    enum class Brand { TJ, KY }
}
