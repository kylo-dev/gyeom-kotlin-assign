package com.assign.backend.domain.user.domain.model

import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.user.entity.Role

data class User(
    val id: UserId,
    val email: Email,
    val password: Password,
    val name: String,
    val role: Role,
    val timestamps: Timestamps
) {
    val userId: Long get() = id.value
    val emailValue: String get() = email.value
}
