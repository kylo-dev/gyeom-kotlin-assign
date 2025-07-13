package com.assign.backend.domain.chat.controller.dto.response

import com.assign.backend.domain.chat.application.dto.response.ChatResult
import java.time.LocalDateTime

data class ChatResponse(
    val id: Long,
    val question: String,
    val answer: String,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun from(result: ChatResult): ChatResponse {
            return ChatResponse(
                result.id.value,
                result.question,
                result.answer,
                result.timestamps.createdAt,
            )
        }
    }
}
