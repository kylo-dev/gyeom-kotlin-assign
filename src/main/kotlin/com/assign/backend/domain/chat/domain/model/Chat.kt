package com.assign.backend.domain.chat.domain.model

import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.thread.domain.model.Thread

data class Chat(
    val id: ChatId,
    val thread: Thread,
    val question: String,
    val answer: String,
    val timestamps: Timestamps,
) {
    fun getUserId(): Long {
        return thread.getUserId()
    }
}
