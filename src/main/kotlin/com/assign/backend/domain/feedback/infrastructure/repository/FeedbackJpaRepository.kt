package com.assign.backend.domain.feedback.infrastructure.repository

import com.assign.backend.domain.feedback.entity.FeedbackEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FeedbackJpaRepository : JpaRepository<FeedbackEntity, Long> {
    @Query("SELECT f FROM FeedbackEntity f WHERE f.user.id = :userId")
    fun findAllByUserId(userId: Long, pageable: Pageable): Page<FeedbackEntity>
    fun existsByChatIdAndUserId(chatId: Long, userId: Long): Boolean

    @Modifying
    @Query("""DELETE FROM FeedbackEntity f 
        WHERE f.chat.id in (SELECT c.id FROM ChatEntity c WHERE c.thread.id = :threadId)""")
    fun deleteFeedbacksByThreadId(@Param("threadId") threadId: Long)

    @Modifying
    @Query("UPDATE FeedbackEntity f SET f.positive = :positive WHERE f.id = :feedbackId")
    fun updatePositive(@Param("feedbackId") feedbackId: Long, @Param("positive") positive: Boolean)
}