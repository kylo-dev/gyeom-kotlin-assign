package com.assign.backend.domain.feedback.controller.dto.response

import com.assign.backend.domain.feedback.domain.Feedback
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
        fun from(feedback: Feedback): FeedbackResponse =
            FeedbackResponse(
                id = feedback.id.value,
                chatId = feedback.chatId.value,
                userId = feedback.userId.value,
                positive = feedback.positive,
                status = feedback.status,
                createdAt = feedback.timestamps.createdAt,
            )
    }
}