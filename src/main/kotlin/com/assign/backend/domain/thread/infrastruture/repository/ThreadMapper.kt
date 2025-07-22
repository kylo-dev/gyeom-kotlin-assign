package com.assign.backend.domain.thread.infrastruture.repository

import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.thread.entity.ThreadEntity
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import com.assign.backend.domain.user.entity.UserEntity
import com.assign.backend.domain.user.infrastructure.repository.UserMapper

object ThreadMapper {
    fun createThread(user: User): ThreadEntity {
        return ThreadEntity(
            user = UserMapper.toEntity(user)
        )
    }

    fun toModel(entity: ThreadEntity): Thread {
        return Thread(
            id = ThreadId(entity.id),
            userId = UserId(entity.user.id),
            timestamps = Timestamps(entity.createdAt, entity.updatedAt)
        )
    }

    fun toEntity(model: Thread): ThreadEntity {
        return ThreadEntity(
            id = model.threadId,
            user = UserEntity.referenceOf(model.userIdValue),
        )
    }
}