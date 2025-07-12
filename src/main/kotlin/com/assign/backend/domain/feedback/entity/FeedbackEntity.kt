package com.assign.backend.domain.feedback.entity

import com.assign.backend.domain.BaseTimeEntity
import com.assign.backend.domain.chat.entity.ChatEntity
import com.assign.backend.domain.user.entity.UserEntity
import jakarta.persistence.*


@Entity
@Table(name = "feedbacks",)
class FeedbackEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    val chat: ChatEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: UserEntity,

    val positive: Boolean,

    @Enumerated(EnumType.STRING)
    var status: FeedbackStatus = FeedbackStatus.PENDING,
): BaseTimeEntity() {
}

enum class FeedbackStatus {
    PENDING, RESOLVED
}