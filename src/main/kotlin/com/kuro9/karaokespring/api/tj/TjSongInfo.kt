package com.kuro9.karaokespring.api.tj

import kotlinx.serialization.Serializable

@Serializable
data class TjSongInfo(
    val id: Long,
    val title: String,
    val singer: String,
    val lyricist: String,
    val composer: String
)
