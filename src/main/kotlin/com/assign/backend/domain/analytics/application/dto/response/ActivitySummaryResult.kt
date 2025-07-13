package com.assign.backend.domain.analytics.application.dto.response

import com.assign.backend.domain.analytics.controller.dto.response.ActivitySummaryResponse

data class ActivitySummaryResult(
    val signupCount: Int,
    val loginCount: Int,
    val chatCount: Int,
) {
    fun toResponse(): ActivitySummaryResponse {
        return ActivitySummaryResponse(
            signupCount,
            loginCount,
            chatCount,
        )
    }
}
