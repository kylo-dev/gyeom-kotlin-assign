package com.assign.backend.domain.feedback.application.dto.reqeust

import com.assign.backend.domain.user.domain.model.User

data class CreateFeedbackCommand(
    val chatId: Long,
    val positive: Boolean,
    val user: User,
)
