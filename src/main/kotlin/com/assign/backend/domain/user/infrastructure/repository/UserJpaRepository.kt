package com.assign.backend.domain.user.infrastructure.repository

import com.assign.backend.domain.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface UserJpaRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun existsByEmail(email: String): Boolean
    fun countByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Int
}