package com.assign.backend.domain.chat.entity

import com.assign.backend.domain.common.BaseTimeEntity
import com.assign.backend.domain.feedback.entity.FeedbackEntity
import com.assign.backend.domain.thread.entity.ThreadEntity
import com.assign.backend.domain.user.entity.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "chats")
class ChatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id")
    val thread: ThreadEntity,

    @OneToMany(mappedBy = "chat", cascade = [CascadeType.ALL], orphanRemoval = true)
    val feedbacks: MutableList<FeedbackEntity> = mutableListOf(),

    val question: String,
    val answer: String,
): BaseTimeEntity() {
}