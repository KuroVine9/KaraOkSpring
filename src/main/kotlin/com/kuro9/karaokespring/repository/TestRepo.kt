package com.kuro9.karaokespring.repository

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface TestRepo {

    @Select("SELECT 1 FROM DUAL")
    fun test(): Long
}