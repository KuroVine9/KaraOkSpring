package com.kuro9.karaokespring.api.tj

import org.jsoup.Jsoup
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import java.time.Duration

@Service
class TJ {
    private val url = "https://www.tjmedia.com/tjsong/song_monthNew.asp"
    private val restTemplate = RestTemplateBuilder()
        .setReadTimeout(Duration.ofSeconds(10))
        .setConnectTimeout(Duration.ofSeconds(20))
        .build()

    @Throws(RestClientException::class)
    fun get(): List<TjSongInfo> {
        val result = restTemplate.getForObject(url, String::class.java) ?: return emptyList()
        val page = Jsoup.parseBodyFragment(result)
        val songTableElement = page.selectXpath("//*[@id=\"BoardType1\"]/table/tbody/tr").also {
            if (it.isEmpty()) return emptyList()
        }
        return songTableElement.drop(1).map { node ->
            val data = node.select("td").map { it.text() }
            val (id, title, singer, lyricist, composer) = data

            TjSongInfo(
                id = id.toLong(),
                title = title,
                singer = singer,
                lyricist = lyricist,
                composer = composer
            )
        }
    }
}