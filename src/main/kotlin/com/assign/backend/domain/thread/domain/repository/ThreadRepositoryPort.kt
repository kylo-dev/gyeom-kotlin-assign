package com.assign.backend.domain.thread.domain.repository

import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ThreadRepositoryPort {
    fun findLatestThread(userId: UserId): Thread?
    fun findAllByUserId(userId: UserId, pageable: Pageable): Page<Thread>
    fun findWithUserById(threadId: ThreadId): Thread?
    fun delete(thread: Thread)
    fun save(user: User): Thread
}