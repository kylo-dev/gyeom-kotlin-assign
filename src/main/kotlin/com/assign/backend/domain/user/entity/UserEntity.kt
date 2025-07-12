package com.assign.backend.domain.user.entity

import com.assign.backend.domain.BaseTimeEntity
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
}

enum class Role {
    MEMBER, ADMIN
}