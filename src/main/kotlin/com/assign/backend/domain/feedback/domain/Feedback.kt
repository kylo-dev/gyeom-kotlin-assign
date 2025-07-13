package com.assign.backend.domain.feedback.domain

import com.assign.backend.domain.chat.domain.model.ChatId
import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.feedback.domain.model.FeedbackId
import com.assign.backend.domain.feedback.entity.FeedbackStatus
import com.assign.backend.domain.user.domain.model.UserId

data class Feedback(
    val id: FeedbackId,
    val chatId: ChatId,
    val userId: UserId,
    val positive: Boolean,
    val status: FeedbackStatus,
    val timestamps: Timestamps,
)
