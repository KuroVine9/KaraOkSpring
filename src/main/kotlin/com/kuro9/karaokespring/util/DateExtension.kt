package com.kuro9.karaokespring.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth

val LocalDateTime.yyyyMMdd: String get() = "%02d%02d%02d".format(year, monthValue, dayOfMonth)
val LocalDate.yyyyMMdd: String get() = "%02d%02d%02d".format(year, monthValue, dayOfMonth)

fun YearMonth.toRangeOfMonth(): ClosedRange<LocalDate> = LocalDate.of(year, monthValue, 1)..this.atEndOfMonth()