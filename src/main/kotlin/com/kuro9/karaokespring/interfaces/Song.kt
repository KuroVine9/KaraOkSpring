package com.kuro9.karaokespring.interfaces

abstract class Song {

    abstract val id: Long
    abstract val brand: Song.Brand

    enum class Brand { TJ, KY }

    override fun equals(other: Any?): Boolean = when (other) {
        null -> false
        !is Song -> false
        else -> this.id == other.id && this.brand == other.brand
    }
}