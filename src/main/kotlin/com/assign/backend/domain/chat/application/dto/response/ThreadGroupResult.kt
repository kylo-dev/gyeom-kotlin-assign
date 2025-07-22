package com.assign.backend.domain.chat.application.dto.response

import com.assign.backend.domain.chat.controller.dto.response.ThreadGroupResponse
import com.assign.backend.domain.thread.domain.model.ThreadId
import org.springframework.data.jpa.domain.AbstractPersistable_.id

data class ThreadGroupResult(
    val threadId: ThreadId,
    val chats: List<ChatResult>
) {
    fun toResponse(): ThreadGroupResponse {
        val chatResponses = chats.map {
            it.toResponse()
        }
        return ThreadGroupResponse(
            threadId = threadId.value,
            chats = chatResponses
        )
    }
}
