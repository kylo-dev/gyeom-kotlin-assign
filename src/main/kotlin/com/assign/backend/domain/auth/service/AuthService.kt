package com.assign.backend.domain.auth.service

import com.assign.backend.domain.auth.controller.dto.request.LoginRequest
import com.assign.backend.domain.auth.controller.dto.request.SignupRequest
import com.assign.backend.domain.user.repository.UserMapper
import com.assign.backend.domain.user.repository.UserRepository
import com.assign.backend.domain.user.service.UserService
import com.assign.backend.global.exception.CustomBadRequestException
import com.assign.backend.global.jwt.JwtProvider
import com.assign.backend.global.jwt.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
) {

    fun signup(request: SignupRequest) {
        validateEmail(request.email)
        val newUser = UserMapper.createUser(
            request.email,
            passwordEncoder.encode(request.password),
            request.name
        )
        userRepository.save(newUser)
    }

    fun login(request: LoginRequest): TokenResponse {
        val user = userService.getUserByEmail(request.email)
        validatePassword(request.password, user.password)
        val token = jwtProvider.generateToken(user.id!!, user.role)
        return TokenResponse(token)
    }

    private fun validateEmail(email: String) {
        if (userRepository.existsByEmail(email)) {
            throw CustomBadRequestException("이메일 또는 비밀번호가 잘못되었습니다.")
        }
    }

    private fun validatePassword(requestPassword: String, userPassword: String) {
        if (!passwordEncoder.matches(requestPassword, userPassword)) {
            throw CustomBadRequestException("이메일 또는 비밀번호가 잘못되었습니다.")
        }
    }
}