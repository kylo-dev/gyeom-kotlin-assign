package com.assign.backend.domain.user.domain.service

import com.assign.backend.domain.user.domain.model.User
import com.assign.backend.domain.user.domain.model.UserId
import com.assign.backend.domain.user.domain.repository.UserRepositoryPort
import com.assign.backend.domain.user.entity.UserEntity
import com.assign.backend.global.exception.CustomBadRequestException
import com.assign.backend.global.util.orBadRequest
import com.assign.backend.global.util.orNotFound
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepositoryPort: UserRepositoryPort
) {

    fun getUserByEmail(email: String): User {
        return userRepositoryPort.findUserByEmail(email)
            .orBadRequest("이메일 또는 비밀번호가 잘못되었습니다.")
    }

    fun getUserById(userId: UserId): User {
        return userRepositoryPort.findUserById(userId)
            .orNotFound("존재하지 않는 회원입니다.")
    }

    fun countTodaySignup(start: LocalDateTime, end: LocalDateTime): Int {
        return userRepositoryPort.countTodaySignup(start, end)
    }

    fun save(entity: UserEntity): User {
        return userRepositoryPort.save(entity)
    }

    fun validateEmail(email: String) {
        if (userRepositoryPort.existsByEmail(email)) {
            throw CustomBadRequestException("이메일 또는 비밀번호가 잘못되었습니다.")
        }
    }

}