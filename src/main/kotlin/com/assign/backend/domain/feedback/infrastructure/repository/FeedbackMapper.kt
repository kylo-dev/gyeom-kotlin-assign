package com.assign.backend.domain.feedback.infrastructure.repository

import com.assign.backend.domain.chat.domain.model.ChatId
import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.feedback.domain.Feedback
import com.assign.backend.domain.feedback.domain.model.FeedbackId
import com.assign.backend.domain.feedback.entity.FeedbackEntity
import com.assign.backend.domain.user.domain.model.UserId

object FeedbackMapper {
    fun toModel(entity: FeedbackEntity): Feedback {
        return Feedback(
            id = FeedbackId(entity.id),
            chatId = ChatId(entity.chat.id),
            userId = UserId(entity.user.id),
            positive = entity.positive,
            status = entity.status,
            timestamps = Timestamps(entity.createdAt, entity.updatedAt),
        )
    }
}