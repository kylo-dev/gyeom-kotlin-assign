package com.assign.backend.domain.user.domain.model

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@JvmInline
value class Password private constructor(
    val value: String
) {
    companion object {
        private val encoder = BCryptPasswordEncoder()

        fun encode(rawPassword: String): String =
            encoder.encode(rawPassword)

        fun of(password: String): Password = Password(password)
    }

    fun matches(rawPassword: String): Boolean =
        encoder.matches(rawPassword, value)
}