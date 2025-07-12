package com.assign.backend.domain.feedback.controller.dto.request

data class CreateFeedbackRequest(
    val chatId: Long,
    val positive: Boolean,
)
