package com.assign.backend.domain.chat.controller.dto.request

data class CreateChatRequest(
    val question: String,
    val isStreaming: Boolean? = false,
    val model: String? = "default",
)
