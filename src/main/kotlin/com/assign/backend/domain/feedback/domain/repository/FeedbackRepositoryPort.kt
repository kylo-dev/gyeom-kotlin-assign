package com.assign.backend.domain.feedback.domain.repository

import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.model.ChatId
import com.assign.backend.domain.feedback.application.dto.reqeust.UpdateFeedbackStatusCommand
import com.assign.backend.domain.feedback.domain.Feedback
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FeedbackRepositoryPort {
    fun findAllByUserId(userId: UserId, pageable: Pageable): Page<Feedback>
    fun existsByChatIdAndUserId(chatId: ChatId, userId: UserId): Boolean
    fun deleteFeedbacksByThreadId(threadId: ThreadId)
    fun save(chat: Chat, user: User, positive: Boolean)
    fun updateFeedbackPositive(request: UpdateFeedbackStatusCommand)
}