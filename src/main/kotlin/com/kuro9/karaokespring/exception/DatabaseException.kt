package com.kuro9.karaokespring.exception

import org.springframework.http.HttpStatus

sealed class DatabaseException(
    override val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    override val message: String? = "Unknown Database Error Occurred.",
    override val cause: Throwable? = null
) : CodeAssignException(httpStatus, message, cause) {

    class InsertFailedException(
        override val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
        override val message: String? = "Data Insert Failed.",
        override val cause: Throwable? = null
    ) : DatabaseException(httpStatus, message, cause)
}