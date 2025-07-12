package com.assign.backend.global.exception

import org.springframework.http.HttpStatus

open class CustomException(
    val httpStatusCode: Int,
    val code: String,
    override val message: String,
) : RuntimeException(){

    constructor(message: String) : this(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "COMMON500",
        message
    )
}