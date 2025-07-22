package com.assign.backend.domain.feedback.controller.dto.request

import com.assign.backend.domain.feedback.application.dto.reqeust.CreateFeedbackCommand
import com.assign.backend.domain.user.domain.model.User

data class CreateFeedbackRequest(
    val chatId: Long,
    val positive: Boolean,
) {
    fun toServiceRequest(user: User): CreateFeedbackCommand {
        return CreateFeedbackCommand(
            chatId,
            positive,
            user,
        )
    }
}
