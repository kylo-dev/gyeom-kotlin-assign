package com.assign.backend.domain.chat.application.dto.response

import com.assign.backend.domain.chat.controller.dto.response.ChatResponse
import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.model.ChatId
import com.assign.backend.domain.common.Timestamps

data class ChatResult(
    val id: ChatId,
    val question: String,
    val answer: String,
    val timestamps: Timestamps,
) {
    companion object {
        fun from(chat: Chat): ChatResult {
            return ChatResult(
                id = chat.id,
                question = chat.question,
                answer = chat.answer,
                timestamps = chat.timestamps,
            )
        }
    }

    fun toResponse(): ChatResponse {
        return ChatResponse(
            id.value,
            question,
            answer,
            timestamps.createdAt,
        )
    }
}
