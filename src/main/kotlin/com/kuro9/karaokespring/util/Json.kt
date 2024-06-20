package com.kuro9.karaokespring.util

import kotlinx.serialization.json.Json

val json: Json = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
}