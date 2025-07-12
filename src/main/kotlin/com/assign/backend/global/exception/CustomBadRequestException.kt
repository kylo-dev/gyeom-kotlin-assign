package com.assign.backend.global.exception

import org.springframework.http.HttpStatus

class CustomBadRequestException(message: String) : CustomException(
    httpStatusCode = HttpStatus.BAD_REQUEST.value(),
    code = "COMMON400",
    message,
) {
}