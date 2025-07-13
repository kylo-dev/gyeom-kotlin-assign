package com.assign.backend.domain.chat.domain.model

import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.user.domain.model.UserId

data class Chat(
    val id: ChatId,
    val userId: UserId,
    val threadId: ThreadId,
    val question: String,
    val answer: String,
    val timestamps: Timestamps,
) {
    val chatId: Long get() = id.value
    val userIdValue: Long get() = userId.value
    val threadIdValue: Long get() = threadId.value
}
