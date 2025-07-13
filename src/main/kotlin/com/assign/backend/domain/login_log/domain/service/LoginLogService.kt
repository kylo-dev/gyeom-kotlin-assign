package com.assign.backend.domain.login_log.domain.service

import com.assign.backend.domain.login_log.infrastructure.repository.LoginLogJpaRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LoginLogService(
    private val loginLogJpaRepository: LoginLogJpaRepository,
) {
    fun countByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Int {
        return loginLogJpaRepository.countByCreatedAtBetween(start, end)
    }
}