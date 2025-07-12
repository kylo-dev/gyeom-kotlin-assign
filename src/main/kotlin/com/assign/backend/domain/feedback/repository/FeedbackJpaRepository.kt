package com.assign.backend.domain.feedback.repository

import com.assign.backend.domain.feedback.entity.FeedbackEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface FeedbackJpaRepository : JpaRepository<FeedbackEntity, Long> {
    fun findByUserId(userId: Long, pageable: Pageable): Page<FeedbackEntity>
    fun findByChatIdAndUserId(chatId: Long, userId: Long): FeedbackEntity?
}