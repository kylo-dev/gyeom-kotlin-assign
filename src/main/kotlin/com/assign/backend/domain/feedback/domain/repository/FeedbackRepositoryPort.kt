package com.assign.backend.domain.feedback.domain.repository

import com.assign.backend.domain.feedback.entity.FeedbackEntity
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.user.domain.model.UserId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FeedbackRepositoryPort {
    fun findByUserId(userId: UserId, pageable: Pageable): Page<FeedbackEntity>
    fun findByChatIdAndUserId(chatId: Long, userId: UserId): FeedbackEntity?
    fun deleteFeedbacksByThreadId(threadId: ThreadId)
}