package com.kuro9.karaokespring.api.tj

import com.kuro9.karaokespring.interfaces.Song
import kotlinx.serialization.Serializable

@Serializable
data class TjSongInfo(
    override val id: Long,
    val title: String,
    val singer: String,
    val lyricist: String,
    val composer: String
) : Song() {
    override val brand = Brand.TJ
}
