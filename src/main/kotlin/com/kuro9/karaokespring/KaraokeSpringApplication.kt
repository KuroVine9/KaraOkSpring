package com.kuro9.karaokespring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KaraokeSpringApplication

fun main(args: Array<String>) {
    runApplication<KaraokeSpringApplication>(*args)
}
