package com.assign.backend.domain.feedback.controller.dto.request

import com.assign.backend.domain.feedback.application.dto.reqeust.UpdateFeedbackStatusCommand

data class UpdateFeedbackStatusRequest(
    // 값 없을 시 false 주입
    val positive: Boolean,
) {
    fun toServiceRequest(feedbackId: Long): UpdateFeedbackStatusCommand {
        return UpdateFeedbackStatusCommand(
            feedbackId,
            positive,
        )
    }
}