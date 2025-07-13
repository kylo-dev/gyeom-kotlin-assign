package com.assign.backend.domain.user.infrastructure.repository

import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import com.assign.backend.domain.user.domain.repository.UserRepositoryPort
import com.assign.backend.domain.user.entity.UserEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UserRepository(
    private val userJpaRepository: UserJpaRepository,
) : UserRepositoryPort {
    override fun findUserByEmail(email: String): User? {
        return userJpaRepository.findByEmail(email)
            ?.let(UserMapper::toModel)
    }

    override fun findUserById(userId: UserId): User? {
        return userJpaRepository.findByIdOrNull(userId.value)
            ?.let(UserMapper::toModel)
    }

    override fun countTodaySignup(start: LocalDateTime, end: LocalDateTime): Int {
        return userJpaRepository.countByCreatedAtBetween(start, end)
    }

    override fun save(userEntity: UserEntity): User {
        return userJpaRepository.save(userEntity).let(UserMapper::toModel)
    }

    override fun existsByEmail(email: String): Boolean {
        return userJpaRepository.existsByEmail(email)
    }
}