package com.assign.backend.global.jwt

data class TokenResponse(
    val accessToken: String,
)

data class TokenResult(
    val accessToken: String,
) {
    fun toResponse(): TokenResponse {
        return TokenResponse(accessToken)
    }
}