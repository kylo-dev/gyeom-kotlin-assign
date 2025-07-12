package com.assign.backend.domain.chat.entity

import com.assign.backend.domain.BaseTimeEntity
import com.assign.backend.domain.thread.entity.ThreadEntity
import jakarta.persistence.*

@Entity
@Table(name = "chats")
class ChatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val thread: ThreadEntity,

    val question: String,
    val answer: String,
): BaseTimeEntity() {
}