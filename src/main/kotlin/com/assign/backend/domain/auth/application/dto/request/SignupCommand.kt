package com.assign.backend.domain.auth.application.dto.request

data class SignupCommand(
    val email: String,
    val password: String,
    val name: String,
)
