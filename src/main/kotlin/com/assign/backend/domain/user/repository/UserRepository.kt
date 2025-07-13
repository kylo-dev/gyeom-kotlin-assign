package com.assign.backend.domain.user.repository

import com.assign.backend.domain.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.time.LocalDateTime

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun existsByEmail(email: String): Boolean
    fun countByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Int
}