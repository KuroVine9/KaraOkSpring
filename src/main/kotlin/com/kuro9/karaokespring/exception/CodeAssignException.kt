package com.kuro9.karaokespring.exception

import org.springframework.http.HttpStatus

class CodeAssignException(
    val httpStatus: HttpStatus,
    override val message: String? = null,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)