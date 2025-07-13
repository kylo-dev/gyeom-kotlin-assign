package com.assign.backend.domain.thread.infrastruture.repository

import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.thread.domain.repository.ThreadRepositoryPort
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class ThreadRepository(
    private val repository: ThreadJpaRepository,
) : ThreadRepositoryPort {
    override fun findLatestThread(userId: UserId): Thread? {
        return repository.findLatestThread(userId.value)
            ?.let(ThreadMapper::toModel)
    }

    override fun findAllByUserId(userId: UserId, pageable: Pageable): Page<Thread> {
        return repository.findAllByUserId(userId.value, pageable).map(ThreadMapper::toModel)
    }

    override fun findWithUserById(threadId: ThreadId): Thread? {
        return repository.findWithUserById(threadId.value)
            ?.let(ThreadMapper::toModel)
    }

    override fun delete(thread: Thread) {
        repository.deleteById(thread.threadId)
    }

    override fun save(user: User): Thread {
        val threadEntity = repository.save(ThreadMapper.createThread(user))
        return ThreadMapper.toModel(threadEntity)
    }
}