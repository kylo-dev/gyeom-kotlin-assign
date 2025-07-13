package com.assign.backend.domain.feedback.controller.dto.request

data class UpdateFeedbackStatusRequest(
    // 값 없을 시 false 주입
    val positive: Boolean,
)