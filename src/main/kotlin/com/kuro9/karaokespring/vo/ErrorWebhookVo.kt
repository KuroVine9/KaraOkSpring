package com.kuro9.karaokespring.vo

import kotlinx.serialization.Serializable

@Serializable
data class Field(
    val name: String,
    val value: String
)

@Serializable
data class Embed(
    val title: String,
    val description: String,
    val color: Int,
    val fields: List<Field>
)

@Serializable
data class Payload(
    val username: String,
    val content: String,
    val embeds: List<Embed>
)