package com.assign.backend.global.exception

data class ValidationErrorDetail(
    val field: String,
    val rejectedValue: Any?,
    val message: String,
)
