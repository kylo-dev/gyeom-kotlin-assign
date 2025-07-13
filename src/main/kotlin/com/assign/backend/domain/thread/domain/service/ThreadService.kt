package com.assign.backend.domain.thread.domain.service

import com.assign.backend.domain.thread.domain.model.Thread
import com.assign.backend.domain.thread.domain.model.ThreadId
import com.assign.backend.domain.thread.domain.repository.ThreadRepositoryPort
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import com.assign.backend.global.exception.CustomBadRequestException
import com.assign.backend.global.util.orNotFound
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ThreadService(
    private val repositoryPort: ThreadRepositoryPort,
) {

    @Transactional
    fun deleteThread(user: User, threadId: Long) {
        val thread = findThreadById(ThreadId(threadId))
        if (thread.getUserId() != user.getUserId()) {
            throw CustomBadRequestException("잘못된 요청입니다.")
        }
        repositoryPort.delete(thread)
    }

    @Transactional
    fun save(user: User): Thread {
        return repositoryPort.save(user)
    }

    // TODO: FETCH JOIN 필요
    fun findThreadById(threadId: ThreadId): Thread {
        return repositoryPort.findWithUserById(threadId)
            .orNotFound("존재하지 않는 스레드입니다.")
    }

    fun findLatestThread(userId: UserId): Thread? {
        return repositoryPort.findLatestThread(userId)
    }

    fun findAllByUserId(userId: UserId, pageable: Pageable): List<Thread> {
        return repositoryPort.findAllByUserId(userId, pageable).content
    }

}