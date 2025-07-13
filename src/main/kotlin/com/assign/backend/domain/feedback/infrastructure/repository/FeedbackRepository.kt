package com.assign.backend.domain.feedback.infrastructure.repository

import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.model.ChatId
import com.assign.backend.domain.chat.infrastructure.repository.ChatMapper
import com.assign.backend.domain.feedback.application.dto.reqeust.UpdateFeedbackStatusCommand
import com.assign.backend.domain.feedback.domain.Feedback
import com.assign.backend.domain.feedback.domain.repository.FeedbackRepositoryPort
import com.assign.backend.domain.feedback.entity.FeedbackEntity
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import com.assign.backend.domain.user.infrastructure.repository.UserMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class FeedbackRepository(
    private val repository: FeedbackJpaRepository
): FeedbackRepositoryPort {
    override fun findAllByUserId(userId: UserId, pageable: Pageable): Page<Feedback> {
        return repository.findAllByUserId(userId.value, pageable)
            .map { FeedbackMapper.toModel(it) }
    }

    // TODO: 수정
    override fun existsByChatIdAndUserId(chatId: ChatId, userId: UserId): Boolean {
        return repository.existsByChatIdAndUserId(chatId.value, userId.value)
    }

    override fun deleteFeedbacksByThreadId(threadId: ThreadId) {
        repository.deleteFeedbacksByThreadId(threadId.value)
    }

    override fun save(chat: Chat, user: User, positive: Boolean) {
        repository.save(
            FeedbackEntity(
                chat = ChatMapper.toEntity(chat),
                user = UserMapper.toEntity(user),
                positive = positive
            )
        )
    }

    override fun updateFeedbackPositive(request: UpdateFeedbackStatusCommand) {
        repository.updatePositive(request.feedbackId, request.positive)
    }
}