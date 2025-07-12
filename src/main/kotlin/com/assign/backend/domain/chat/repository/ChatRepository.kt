package com.assign.backend.domain.chat.repository

import com.assign.backend.domain.chat.entity.ChatEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRepository: JpaRepository<ChatEntity, Long> {
    fun findByThreadId(threadId: Long, pageable: Pageable): Page<ChatEntity>
}