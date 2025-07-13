package com.assign.backend.domain.chat.domain.model

import com.assign.backend.domain.chat.entity.ChatEntity
import com.assign.backend.domain.chat.infrastructure.repository.ChatMapper
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.infrastructure.repository.UserMapper

data class UserChat(
    val user: User,
    val chat: Chat,
) {
    companion object {
        fun from(entity: ChatEntity): UserChat {
            return UserChat(
                user = UserMapper.toModel(entity.user),
                chat = ChatMapper.toModel(entity),
            )
        }
    }
}
