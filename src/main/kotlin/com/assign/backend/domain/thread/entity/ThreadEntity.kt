package com.assign.backend.domain.thread.entity

import com.assign.backend.domain.BaseTimeEntity
import com.assign.backend.domain.user.entity.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "threads")
class ThreadEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: UserEntity,
): BaseTimeEntity() {
}