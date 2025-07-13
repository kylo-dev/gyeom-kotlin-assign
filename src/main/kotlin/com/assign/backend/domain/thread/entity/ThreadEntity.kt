package com.assign.backend.domain.thread.entity

import com.assign.backend.domain.common.BaseTimeEntity
import com.assign.backend.domain.chat.entity.ChatEntity
import com.assign.backend.domain.user.entity.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "threads")
class ThreadEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @OneToMany(mappedBy = "thread", cascade = [CascadeType.ALL], orphanRemoval = true)
    val chats: MutableList<ChatEntity> = mutableListOf()
): BaseTimeEntity() {
}