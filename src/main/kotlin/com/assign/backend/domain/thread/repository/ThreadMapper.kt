package com.assign.backend.domain.thread.repository

import com.assign.backend.domain.thread.entity.ThreadEntity
import com.assign.backend.domain.user.entity.UserEntity

object ThreadMapper {
    fun createThread(user: UserEntity): ThreadEntity {
        return ThreadEntity(
            user = user
        )
    }
}