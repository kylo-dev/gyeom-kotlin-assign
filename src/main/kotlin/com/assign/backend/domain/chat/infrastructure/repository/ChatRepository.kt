package com.assign.backend.domain.chat.infrastructure.repository

import com.assign.backend.domain.chat.entity.ChatEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ChatRepository: JpaRepository<ChatEntity, Long> {
    fun findByThreadId(threadId: Long, pageable: Pageable): Page<ChatEntity>
    fun countByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Int
    fun findAllByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): List<ChatEntity>
}