package com.assign.backend.domain.auth.application.dto.request

data class LoginCommand(
    val email: String,
    val password: String,
)
