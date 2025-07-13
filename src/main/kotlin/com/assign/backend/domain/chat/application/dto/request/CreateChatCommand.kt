package com.assign.backend.domain.chat.application.dto.request

data class CreateChatCommand(
    val question: String,
    val isStreaming: Boolean?,
    val model: String?,
)
