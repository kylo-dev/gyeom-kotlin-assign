package com.assign.backend.global.jwt

import com.assign.backend.domain.user.domain.model.UserId
import com.assign.backend.domain.user.entity.Role
import com.assign.backend.domain.user.domain.service.UserService
import com.assign.backend.global.util.logger
import com.assign.backend.global.security.CustomAuthentication
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProvider(
    @Value("\${jwt.secret}") secretKey: String,
    private val userService: UserService,
) {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray())
    private val expirationMs = 24 * 60 * 60 * 7000

    fun generateToken(userId: UserId, role: Role): String {
        val now = Date()
        val expiryDate = Date(now.time + expirationMs)

        return Jwts.builder()
            .claims(
                mapOf(
                    "userId" to userId.value,
                    "role" to role.toString()
                )
            )
            .issuedAt(now)
            .expiration(expiryDate)
            .signWith(secretKey)
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        return try {
            getClaims(token)
            true
        } catch (e: Exception) {
            when (e) {
                is SecurityException, is MalformedJwtException -> {
                    logger.info { "Invalid JWT $e" }
                }

                is ExpiredJwtException -> {
                    logger.info { "Expired JWT $e" }
                }

                is UnsupportedJwtException -> {
                    logger.info { "Unsupported JWT $e" }
                }

                is IllegalArgumentException -> {
                    logger.info { "JWT claims string is empty $e" }
                }
            }
            false
        }
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claims = getClaims(accessToken)
        val userId = (claims["userId"] as Number).toLong()
        val user = userService.getUserById(UserId(userId))
        return CustomAuthentication(user)
    }
}