package com.assign.backend.domain.user.entity

import com.assign.backend.domain.common.BaseTimeEntity
import com.assign.backend.domain.thread.entity.ThreadEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val email: String,
    val password: String,
    val name: String,
    @Enumerated(EnumType.STRING)
    val role: Role,
): BaseTimeEntity() {
    companion object {
        fun referenceOf(id: Long): UserEntity {
            return UserEntity(
                id = id,
                email = "",
                password = "",
                name = "",
                role = Role.MEMBER,
            )
        }
    }
}

enum class Role {
    MEMBER, ADMIN
}