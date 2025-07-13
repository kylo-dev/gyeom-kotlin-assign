package com.assign.backend.domain.thread.infrastruture.repository

import com.assign.backend.domain.thread.entity.ThreadEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ThreadJpaRepository : JpaRepository<ThreadEntity, Long> {
    @Query(
        """
        SELECT t FROM ThreadEntity t
        WHERE t.user.id = :userId
        ORDER BY t.createdAt DESC 
        limit 1
    """
    )
    fun findLatestThread(userId: Long): ThreadEntity?

//    @EntityGraph(attributePaths = ["user"])
    @Query("""
        SELECT t FROM ThreadEntity t
        JOIN FETCH t.user
        WHERE t.user.id = :userId
    """)
    fun findAllByUserId(userId: Long, pageable: Pageable): Page<ThreadEntity>

    @EntityGraph(attributePaths = ["user"])
    fun findWithUserById(id: Long): ThreadEntity?
}