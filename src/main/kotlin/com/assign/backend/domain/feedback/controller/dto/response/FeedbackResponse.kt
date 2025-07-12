package com.assign.backend.domain.feedback.controller.dto.response

import com.assign.backend.domain.feedback.entity.FeedbackEntity
import com.assign.backend.domain.feedback.entity.FeedbackStatus
import java.time.LocalDateTime

data class FeedbackResponse(
    val id: Long,
    val chatId: Long,
    val userId: Long,
    val positive: Boolean,
    val status: FeedbackStatus,
    val createdAt: LocalDateTime
) {
    companion object {
        fun toResponse(feedback: FeedbackEntity): FeedbackResponse =
            FeedbackResponse(
                id = feedback.id,
                chatId = feedback.chat.id,
                userId = feedback.user.id,
                positive = feedback.positive,
                status = feedback.status,
                createdAt = feedback.createdAt!!,
            )
    }
}