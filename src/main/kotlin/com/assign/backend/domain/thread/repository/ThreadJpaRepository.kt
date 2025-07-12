package com.assign.backend.domain.thread.repository

import com.assign.backend.domain.thread.entity.ThreadEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ThreadJpaRepository: JpaRepository<ThreadEntity, Long> {
    fun findFirstByUserIdOrderByCreatedAtDesc(userId: Long): ThreadEntity?
    fun findAllByUserId(userId: Long): List<ThreadEntity>
}