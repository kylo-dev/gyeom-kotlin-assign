package com.assign.backend.domain.chat.controller.dto.response

import com.assign.backend.domain.chat.controller.dto.ChatResponse

data class ThreadGroupResponse(
    val threadId: Long,
    val chats: List<ChatResponse>,
) {
    companion object {
        fun of(threadId: Long, chats: List<ChatResponse>): ThreadGroupResponse {
            return ThreadGroupResponse(
                threadId,
                chats,
            )
        }
    }
}
