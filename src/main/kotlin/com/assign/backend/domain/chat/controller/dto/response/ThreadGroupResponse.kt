package com.assign.backend.domain.chat.controller.dto.response

data class ThreadGroupResponse(
    val threadId: Long,
    val chats: List<ChatResponse>,
) {
}
