package com.assign.backend.domain.user.domain.repository

import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import com.assign.backend.domain.user.entity.UserEntity
import java.time.LocalDateTime

interface UserRepositoryPort {

    fun findUserByEmail(email: String): User?

    fun findUserById(userId: UserId): User?

    fun countTodaySignup(start: LocalDateTime, end: LocalDateTime): Int

    fun save(userEntity: UserEntity): User

    fun existsByEmail(email: String): Boolean
}