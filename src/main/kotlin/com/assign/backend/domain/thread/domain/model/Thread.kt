package com.assign.backend.domain.thread.domain.model

import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.user.domain.model.UserId
import java.time.LocalDateTime


data class Thread(
    val id: ThreadId,
    val userId: UserId,
    val timestamps: Timestamps,
) {
    companion object {
        private const val EXPIRATION_MIN = 30L
    }

    fun isExpiredThread(now: LocalDateTime): Boolean {
        return timestamps.createdAt.plusMinutes(EXPIRATION_MIN).isBefore(now)
    }

    val threadId: Long get() = id.value
    val userIdValue: Long get() = userId.value
}
