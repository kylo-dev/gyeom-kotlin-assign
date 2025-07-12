package com.assign.backend.domain.feedback.controller.dto.request

import com.assign.backend.domain.feedback.entity.FeedbackStatus

data class UpdateFeedbackStatusRequest(
    val status: FeedbackStatus,
)