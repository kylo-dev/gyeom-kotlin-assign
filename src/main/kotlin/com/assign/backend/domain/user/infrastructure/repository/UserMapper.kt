package com.assign.backend.domain.user.infrastructure.repository

import com.assign.backend.domain.common.Timestamps
import com.assign.backend.domain.user.domain.model.Email
import com.assign.backend.domain.user.domain.model.Password
import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import com.assign.backend.domain.user.entity.Role
import com.assign.backend.domain.user.entity.UserEntity
import org.springframework.data.jpa.domain.AbstractPersistable_.id

object UserMapper {
    fun createUser(email: String, password: String, name: String): UserEntity {
        return UserEntity(
            email = email,
            password = password,
            name = name,
            role = Role.MEMBER,
        )
    }

    fun toModel(entity: UserEntity): User {
        return User(
            id = UserId(entity.id),
            email = Email(entity.email),
            password = Password.of(entity.password),
            name = entity.name,
            role = entity.role,
            timestamps = Timestamps(entity.createdAt, entity.updatedAt),
        )
    }

    fun toEntity(model: User): UserEntity {
        return UserEntity(
            id = model.getUserId(),
            email = model.email.value,
            password = model.password.value,
            name = model.name,
            role = model.role,
        )
    }
}