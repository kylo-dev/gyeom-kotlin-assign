package com.assign.backend.domain.login_log.repository

import com.assign.backend.domain.login_log.entity.LoginLogEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface LoginLogJpaRepository: JpaRepository<LoginLogEntity, Long> {
    fun countByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Int
}