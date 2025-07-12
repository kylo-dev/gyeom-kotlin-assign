package com.assign.backend.domain.user.repository

import com.assign.backend.domain.user.entity.Role
import com.assign.backend.domain.user.entity.UserEntity

object UserMapper {

    fun createUser(email: String, password: String, name: String): UserEntity {
        return UserEntity(
            email = email,
            password = password,
            name = name,
            role = Role.MEMBER,
        )
    }
}