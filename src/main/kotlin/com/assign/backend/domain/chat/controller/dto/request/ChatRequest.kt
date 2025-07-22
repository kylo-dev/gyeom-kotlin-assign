package com.assign.backend.domain.chat.controller.dto.request

import com.assign.backend.domain.chat.application.dto.request.CreateChatCommand
import com.assign.backend.domain.user.domain.model.User

data class CreateChatRequest(
    val question: String,
    val isStreaming: Boolean? = false,
    val model: String? = "default",
) {
    fun toServiceRequest(user: User): CreateChatCommand {
        return CreateChatCommand(
            user,
            question,
            isStreaming,
            model,
        )
    }
}
