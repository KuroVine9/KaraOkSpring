package com.kuro9.karaokespring.repository.dao

import com.kuro9.karaokespring.repository.entity.NewSong
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface NewSongLogDao {

    @Insert(
        """
            INSERT INTO NEW_SONG_LOG(ID, BRAND, TITLE, SINGER, LYRICIST, COMPOSER, YYYYMMDD, CREATEDAT)
            VALUES (
                #{id},
                #{brand},
                #{title},
                #{singer},
                #{lyricist},
                #{composer},
                #{yyyyMMdd},
                #{createdAt}
            )
        """
    )
    fun save(song: NewSong): Int

    @Select(
        """
            SELECT *
            FROM NEW_SONG_LOG
            WHERE ID = #{id}
            AND BRAND = #{brand}
        """
    )
    fun findByPk(
        @Param("id") id: Long,
        @Param("brand") brand: String
    ): NewSong?

    @Select(
        """
           SELECT *
            FROM NEW_SONG_LOG
            WHERE BRAND = #{brand}
            AND YYYYMMDD BETWEEN #{yyyyMMddStart} AND #{yyyyMMddEnd}
        """
    )
    fun findByRange(
        @Param("brand") brand: String,
        @Param("yyyyMMddStart") yyyyMMddStart: String,
        @Param("yyyyMMddEnd") yyyyMMddEnd: String
    ): List<NewSong>
}