package com.assign.backend.domain.feedback.infrastructure.repository

import com.assign.backend.domain.feedback.entity.FeedbackEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FeedbackJpaRepository : JpaRepository<FeedbackEntity, Long> {
    fun findByUserId(userId: Long, pageable: Pageable): Page<FeedbackEntity>
    fun findByChatIdAndUserId(chatId: Long, userId: Long): FeedbackEntity?

    @Modifying
    @Query("""DELETE FROM FeedbackEntity f 
        WHERE f.chat.id in (SELECT c.id FROM ChatEntity c WHERE c.thread.id = :threadId)""")
    fun deleteFeedbacksByThreadId(@Param("threadId") threadId: Long)
}