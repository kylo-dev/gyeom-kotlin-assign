package com.assign.backend.domain.auth.controller.dto.request

data class SignupRequest(
    val email: String,
    val password: String,
    val name: String,
)
