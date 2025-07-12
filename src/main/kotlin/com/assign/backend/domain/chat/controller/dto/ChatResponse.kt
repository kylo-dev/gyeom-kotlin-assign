package com.assign.backend.domain.chat.controller.dto

import com.assign.backend.domain.chat.entity.ChatEntity
import java.time.LocalDateTime

data class ChatResponse(
    val id: Long,
    val question: String,
    val answer: String,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun of(chat: ChatEntity): ChatResponse {
            return ChatResponse(
                chat.id!!,
                chat.question,
                chat.answer,
                chat.createdAt!!,
            )
        }
    }
}
