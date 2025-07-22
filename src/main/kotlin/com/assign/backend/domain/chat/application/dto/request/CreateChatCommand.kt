package com.assign.backend.domain.chat.application.dto.request

import com.assign.backend.domain.user.domain.model.User

data class CreateChatCommand(
    val user: User,
    val question: String,
    val isStreaming: Boolean?,
    val model: String?,
)
