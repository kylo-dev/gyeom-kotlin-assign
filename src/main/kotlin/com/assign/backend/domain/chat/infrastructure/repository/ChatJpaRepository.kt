package com.assign.backend.domain.chat.infrastructure.repository

import com.assign.backend.domain.chat.entity.ChatEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface ChatJpaRepository : JpaRepository<ChatEntity, Long> {
    fun findByThreadId(threadId: Long, pageable: Pageable): Page<ChatEntity>
    fun countByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Int

    @EntityGraph(attributePaths = ["user"])
    fun findAllByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): List<ChatEntity>

    @Modifying
    @Query("DELETE FROM ChatEntity c WHERE c.thread.id = :threadId")
    fun deleteChatsByThreadId(@Param("threadId") threadId: Long)

    @Query(
        """
        SELECT * FROM (
        SELECT 
            *, ROW_NUMBER() OVER (PARTITION BY thread_id ORDER BY created_at DESC) as rn
        FROM chats
        WHERE thread_id IN :threadIds
    ) sub
    WHERE sub.rn <= :limit
    """,
        nativeQuery = true
    )
    fun findTopNChatsByThreadIds(
        @Param("threadIds") threadIds: List<Long>,
        @Param("limit") limit: Int
    ): List<ChatEntity>
}