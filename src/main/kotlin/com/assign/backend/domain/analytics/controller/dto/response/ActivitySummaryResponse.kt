package com.assign.backend.domain.analytics.controller.dto.response

data class ActivitySummaryResponse(
    val signupCount: Int,
    val loginCount: Int,
    val chatCount: Int,
)