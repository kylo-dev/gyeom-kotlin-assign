package com.assign.backend.domain.chat.controller.dto.request

import com.assign.backend.domain.chat.application.dto.request.CreateChatCommand

data class CreateChatRequest(
    val question: String,
    val isStreaming: Boolean? = false,
    val model: String? = "default",
) {
    fun toServiceRequest(): CreateChatCommand {
        return CreateChatCommand(
            question,
            isStreaming,
            model,
        )
    }
}
