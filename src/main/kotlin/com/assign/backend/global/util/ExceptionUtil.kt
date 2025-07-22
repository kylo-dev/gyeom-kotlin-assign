package com.assign.backend.global.util

import com.assign.backend.global.exception.CustomBadRequestException
import com.assign.backend.global.exception.CustomNotFoundException

fun <T: Any> T?.orNotFound(message: String): T =
    this ?: throw CustomNotFoundException(message)

fun <T: Any> T?.orBadRequest(message: String): T =
    this ?: throw CustomBadRequestException(message)