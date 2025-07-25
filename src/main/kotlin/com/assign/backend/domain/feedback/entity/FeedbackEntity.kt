package com.assign.backend.domain.feedback.entity

import com.assign.backend.domain.common.BaseTimeEntity
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
    @JoinColumn(name = "chat_id")
    val chat: ChatEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    var positive: Boolean,

    @Enumerated(EnumType.STRING)
    var status: FeedbackStatus = FeedbackStatus.PENDING,
): BaseTimeEntity() {
    fun changePositive(positive: Boolean) {
        this.positive = positive
    }
}

enum class FeedbackStatus {
    PENDING, RESOLVED
}