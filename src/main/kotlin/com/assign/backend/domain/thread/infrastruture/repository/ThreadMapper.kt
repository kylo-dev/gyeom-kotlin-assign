package com.assign.backend.domain.thread.infrastruture.repository

import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.thread.entity.ThreadEntity
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import com.assign.backend.domain.user.infrastructure.repository.UserMapper

object ThreadMapper {
    fun createThread(user: User): ThreadEntity {
        val userEntity = UserMapper.toEntity(user)
        return ThreadEntity(
            user = userEntity
        )
    }

    fun toModel(entity: ThreadEntity): Thread {
        return Thread(
            id = ThreadId(entity.id),
            user = UserMapper.toModel(entity.user),
            timestamps = Timestamps(entity.createdAt, entity.updatedAt)
        )
    }

    fun toEntity(model: Thread): ThreadEntity {
        val userEntity = UserMapper.toEntity(model.user)
        return ThreadEntity(
            id = model.getThreadId(),
            user = userEntity,
        )
    }
}