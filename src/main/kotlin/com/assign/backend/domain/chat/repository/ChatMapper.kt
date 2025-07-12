package com.assign.backend.domain.chat.repository

import com.assign.backend.domain.chat.entity.ChatEntity
import com.assign.backend.domain.thread.entity.ThreadEntity

object ChatMapper {
    fun createChat(thread: ThreadEntity, question: String, answer: String): ChatEntity {
        return ChatEntity(
            thread = thread,
            question = question,
            answer = answer
        )
    }
}