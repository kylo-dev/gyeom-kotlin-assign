package com.assign.backend.domain.feedback.application.dto.reqeust

data class UpdateFeedbackStatusCommand(
    val feedbackId: Long,
    val positive: Boolean,
)
