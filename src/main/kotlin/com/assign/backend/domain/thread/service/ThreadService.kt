package com.assign.backend.domain.thread.service

import com.assign.backend.domain.thread.entity.ThreadEntity
import com.assign.backend.domain.thread.repository.ThreadJpaRepository
import com.assign.backend.global.exception.CustomBadRequestException
import com.assign.backend.global.exception.CustomNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ThreadService(
    private val threadJpaRepository: ThreadJpaRepository,
) {

    @Transactional
    fun deleteThread(userId: Long, threadId: Long) {
        val thread = findThreadById(threadId)
        if (thread.user.id != userId) {
            throw CustomBadRequestException("잘못된 요청입니다.")
        }
        threadJpaRepository.delete(thread)
    }

    fun findThreadById(threadId: Long): ThreadEntity {
        return threadJpaRepository.findByIdOrNull(threadId)
            ?: throw CustomNotFoundException("존재하지 않는 스레드입니다.")
    }

}