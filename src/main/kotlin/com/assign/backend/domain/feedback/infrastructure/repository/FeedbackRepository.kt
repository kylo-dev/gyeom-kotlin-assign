package com.assign.backend.domain.feedback.infrastructure.repository

import com.assign.backend.domain.feedback.domain.repository.FeedbackRepositoryPort
import com.assign.backend.domain.feedback.entity.FeedbackEntity
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.user.domain.model.UserId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class FeedbackRepository(
    private val repository: FeedbackJpaRepository
): FeedbackRepositoryPort {
    override fun findByUserId(userId: UserId, pageable: Pageable): Page<FeedbackEntity> {
        TODO("Not yet implemented")
    }

    override fun findByChatIdAndUserId(chatId: Long, userId: UserId): FeedbackEntity? {
        TODO("Not yet implemented")
    }

    override fun deleteFeedbacksByThreadId(threadId: ThreadId) {
        repository.deleteFeedbacksByThreadId(threadId.value)
    }
}