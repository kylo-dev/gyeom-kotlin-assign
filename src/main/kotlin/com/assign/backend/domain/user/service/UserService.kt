package com.assign.backend.domain.user.service

import com.assign.backend.domain.user.entity.UserEntity
import com.assign.backend.domain.user.repository.UserRepository
import com.assign.backend.global.exception.CustomBadRequestException
import com.assign.backend.global.exception.CustomNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun getUserByEmail(email: String): UserEntity {
        return userRepository.findByEmail(email)
            ?: throw CustomBadRequestException("이메일 또는 비밀번호가 잘못되었습니다.")
    }

    fun getUserById(userId: Long): UserEntity {
        return userRepository.findByIdOrNull(userId)
            ?: throw CustomNotFoundException("존재하지 않는 회원입니다.")
    }
}