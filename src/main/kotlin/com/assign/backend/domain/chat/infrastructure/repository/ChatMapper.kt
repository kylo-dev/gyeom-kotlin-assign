package com.assign.backend.domain.chat.infrastructure.repository

import com.assign.backend.domain.chat.domain.model.Chat
import com.assign.backend.domain.chat.domain.model.ChatId
import com.assign.backend.domain.chat.entity.ChatEntity
import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.thread.entity.ThreadEntity
import com.assign.backend.domain.user.domain.model.UserId
import com.assign.backend.domain.user.entity.UserEntity

object ChatMapper {
    fun createChat(thread: Thread, question: String, answer: String): ChatEntity {
        return ChatEntity(
            user = UserEntity.referenceOf(thread.userIdValue),
            thread = ThreadEntity.referenceOf(thread.threadId),
            question = question,
            answer = answer
        )
    }

    fun toModel(entity: ChatEntity): Chat {
        return Chat(
            id = ChatId(entity.id),
            userId = UserId(entity.user.id),
            threadId = ThreadId(entity.thread.id),
            question = entity.question,
            answer = entity.answer,
            timestamps = Timestamps(entity.createdAt, entity.updatedAt)
        )
    }

    fun toEntity(model: Chat): ChatEntity {
        return ChatEntity(
            id = model.id.value,
            user = UserEntity.referenceOf(model.userIdValue),
            thread = ThreadEntity.referenceOf(model.threadIdValue),
            question = model.question,
            answer = model.answer,
        )
    }
}