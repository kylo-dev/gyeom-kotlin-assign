package com.assign.backend.domain.thread.domain.model

import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.user.domain.model.User
import java.time.LocalDateTime


data class Thread(
    val id: ThreadId,
    val user: User,
    val timestamps: Timestamps,
) {
    companion object {
        private const val EXPIRATION_MIN = 30L
    }

    fun isExpiredThread(now: LocalDateTime): Boolean {
        return timestamps.createdAt.plusMinutes(EXPIRATION_MIN).isBefore(now)
    }

    fun getThreadId(): Long {
        return id.value
    }

    fun getUserId(): Long {
        return user.getUserId()
    }
}
