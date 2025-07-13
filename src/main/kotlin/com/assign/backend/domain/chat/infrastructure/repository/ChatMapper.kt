package com.assign.backend.domain.chat.infrastructure.repository

import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.model.ChatId
import com.assign.backend.domain.chat.entity.ChatEntity
import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.domain.thread.entity.ThreadEntity
import com.assign.backend.domain.thread.infrastruture.repository.ThreadMapper

object ChatMapper {
    fun createChat(thread: Thread, question: String, answer: String): ChatEntity {
        val threadEntity = ThreadMapper.toEntity(thread)
        return ChatEntity(
            thread = threadEntity,
            question = question,
            answer = answer
        )
    }

    fun toModel(entity: ChatEntity): Chat {
        return Chat(
            id = ChatId(entity.id),
            thread = ThreadMapper.toModel(entity.thread),
            question = entity.question,
            answer = entity.answer,
            timestamps = Timestamps(entity.createdAt, entity.updatedAt)
        )
    }

    fun toEntity(model: Chat): ChatEntity {
        return ChatEntity(
            id = model.id.value,
            thread = ThreadMapper.toEntity(model.thread),
            question = model.question,
            answer = model.answer,
        )
    }
}